package com.icecream.shares.controller;

import com.alibaba.fastjson.JSONObject;
import com.icecream.shares.pojo.ResponseJson;
import com.icecream.shares.pojo.ResultCode;
import com.icecream.shares.vo.DecideVo;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dqbryant
 * @create 2020/11/19 10:32
 */
@RestController
@CrossOrigin
public class DecideController {
    @PostMapping("/decide")
    public ResponseJson<Map<String, String>> getDecision(@RequestBody DecideVo decideVo){
        Map<String, String> map = new HashMap<>(10);
        map.put("postId", "1");
        map.put("title", "222");
        map.put("ids", JSONObject.toJSONString(decideVo.getPostIds()));
        return new ResponseJson<>(ResultCode.SUCCESS, map);
    }
}
