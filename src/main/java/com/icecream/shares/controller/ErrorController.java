package com.icecream.shares.controller;




import com.icecream.shares.exception.UnloginException;
import com.icecream.shares.pojo.ResponseJson;
import com.icecream.shares.pojo.ResultCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler(value = UnloginException.class)
    public ResponseJson unloginExceptionHandler(UnloginException e){
        return new ResponseJson(ResultCode.UNLOGIN);
    }


}
