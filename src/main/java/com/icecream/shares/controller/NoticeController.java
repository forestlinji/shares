package com.icecream.shares.controller;

import com.icecream.shares.annotation.Auth;
import com.icecream.shares.interceptor.LoginInterceptor;
import com.icecream.shares.pojo.Notice;
import com.icecream.shares.pojo.ResponseJson;
import com.icecream.shares.pojo.ResultCode;
import com.icecream.shares.service.NoticeService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author dqbryant
 * @create 2020/11/17 15:55
 */
@Slf4j
@CrossOrigin
@RestController()
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    NoticeService noticeServiceImpl;
    @Auth
    @GetMapping("/show")
    public ResponseJson<List<Notice>> show(){
        Integer userId = Integer.parseInt(LoginInterceptor.getUserId());
        return new ResponseJson<>(ResultCode.SUCCESS, noticeServiceImpl.getNoticesById(userId));
    }
    @Auth()
    @DeleteMapping("/delete")
    public ResponseJson<Object> delete(Integer noticeId){
        log.warn("notice:" + noticeId);
        Integer userId = Integer.parseInt(LoginInterceptor.getUserId());
        Notice notice = noticeServiceImpl.getById(noticeId);
        System.out.println(notice);
        if(notice == null || !notice.getAcceptId().equals(userId)){
            return new ResponseJson<>(ResultCode.UNVALIDPARAMS);
        }else {
            noticeServiceImpl.removeById(noticeId);
            return new ResponseJson<>(ResultCode.SUCCESS);
        }
    }
}
