package com.icecream.shares.controller;

import cn.hutool.core.util.PhoneUtil;
import cn.hutool.core.util.RandomUtil;
import com.aliyun.oss.ClientException;
import com.icecream.shares.pojo.*;
import com.icecream.shares.service.MessageService;
import com.icecream.shares.service.UserService;
import com.icecream.shares.utils.JwtTokenUtils;
import com.icecream.shares.utils.MD5Utils;
import com.icecream.shares.vo.LoginPhoneVo;
import com.icecream.shares.vo.LoginUsernameVo;
import com.icecream.shares.vo.WechatLogin;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequestMapping("/user")
@RestController
public class AuthController {
    @Autowired
    public UserService userService;
    @Autowired
    public StringRedisTemplate redisTemplate;
    @Autowired
    public MessageService messageService;



    //单个参数post传参演示
    @PostMapping("test")
    public String test(@RequestBody Map map){
        String username = (String) map.get("username");
        return username;
    }

    @PostMapping("/login/phone")
    public ResponseJson loginByPhone(@Valid @RequestBody LoginPhoneVo loginPhoneVo, HttpServletResponse response){
        String phone = loginPhoneVo.getPhone();
        String code = loginPhoneVo.getCode();
        String correct = redisTemplate.opsForValue().get("code:"+phone);
        if(!Objects.equals(code, correct)){
            return new ResponseJson(ResultCode.WRONGCODE);
        }
        redisTemplate.delete("code:"+phone);
        User user = userService.findUserByPhone(phone);
        boolean hasRegister = true;
        if(user == null){
            hasRegister = false;
            user = new User();
            user.setPhone(phone);
            user.setRegisterTime(new Timestamp(System.currentTimeMillis()));
            user.setLastChangetime(user.getRegisterTime());
            userService.register(user);
        }
        Integer userId = user.getUserId();
        List<String> roles = userService.getRolesByUserId(userId);
        String token = JwtTokenUtils.createToken(userId.toString(), roles, true);
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        response.addHeader("Authorization", token);
//        User user = userService.findUserByPhone("18959121464");
        if (hasRegister) {
            return new ResponseJson(ResultCode.SUCCESS);
        }else{
            return new ResponseJson(ResultCode.REGISTER);
        }
    }

    @PostMapping("/login/wechat")
    public ResponseJson loginByWechat(@RequestBody Map map, HttpServletResponse response){
        Object uname = map.get("code");
        if (uname == null) {
            return new ResponseJson(ResultCode.UNVALIDPARAMS);
        }
        String code = (String) uname;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<WechatLogin> wechatResponse = restTemplate.getForEntity("https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=" + code + "&grant_type=authorization_code",
                WechatLogin.class);
        WechatLogin wechatLogin = wechatResponse.getBody();
        if(!wechatLogin.getErrcode().equals(0)){
            return new ResponseJson(ResultCode.UNVALIDPARAMS);
        }
        String openid = wechatLogin.getOpenid();
        boolean hasRegister = true;
        User user = userService.findUserByOpenId(openid);
        if(user == null){
            hasRegister = false;
            user = new User();
            user.setOpenId(openid);
            user.setRegisterTime(new Timestamp(System.currentTimeMillis()));
            user.setLastChangetime(user.getRegisterTime());
            userService.register(user);
        }
        Integer userId = user.getUserId();
        List<String> roles = userService.getRolesByUserId(userId);
        String token = JwtTokenUtils.createToken(userId.toString(), roles, true);
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        response.addHeader("Authorization", token);
//        User user = userService.findUserByPhone("18959121464");
        if (hasRegister) {
            return new ResponseJson(ResultCode.SUCCESS);
        }else{
            return new ResponseJson(ResultCode.REGISTER);
        }
    }


    @PostMapping("/login/username")
    public ResponseJson loginByUsername(@RequestBody @Valid LoginUsernameVo loginUsernameVo, HttpServletResponse response){
        String username = loginUsernameVo.getUsername();
        String password = loginUsernameVo.getPassword();
        User user = userService.findUserByUsername(username);
        if (user == null) {
            return new ResponseJson(ResultCode.UNVALIDPARAMS);
        }
        String encryptPassword = MD5Utils.cptry(user.getUserId().toString() + password + user.getUserId().toString());
        if(user.getPassword()==null || !user.getPassword().equals(encryptPassword)){
            return new ResponseJson(ResultCode.UNVALIDPARAMS);
        }
        Integer userId = user.getUserId();
        List<String> roles = userService.getRolesByUserId(userId);
        String token = JwtTokenUtils.createToken(userId.toString(), roles, true);
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        response.addHeader("Authorization", token);
        return new ResponseJson(ResultCode.SUCCESS);
    }

    @GetMapping("sendCode")
    @Transactional
    public ResponseJson sendCode(String phone) throws Exception {
        if(!PhoneUtil.isMobile(phone)){
            return new ResponseJson(ResultCode.UNVALIDPARAMS);
        }
        Long expire = redisTemplate.getExpire("code:" + phone);
        if(expire > 240){
            return new ResponseJson(ResultCode.TOOOFTEN);
        }
        int randomInt = RandomUtil.randomInt(100000, 999999);
        String code = String.valueOf(randomInt);
        messageService.sendCode(phone, code);
        return new ResponseJson(ResultCode.SUCCESS);
    }
}
