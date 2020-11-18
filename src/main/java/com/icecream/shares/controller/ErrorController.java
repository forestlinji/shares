package com.icecream.shares.controller;




import com.icecream.shares.exception.UnloginException;
import com.icecream.shares.pojo.ResponseJson;
import com.icecream.shares.pojo.ResultCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler(value = UnloginException.class)
    public ResponseJson unloginExceptionHandler(UnloginException e){
        return new ResponseJson(ResultCode.UNLOGIN);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseJson methodArgumentNotValid(){
        return new ResponseJson(ResultCode.UNVALIDPARAMS);
    }

    @ExceptionHandler(value = {ExpiredJwtException.class, SignatureException.class, MalformedJwtException.class,IllegalArgumentException.class})
    public ResponseJson wrongJwt(){
        return new ResponseJson(ResultCode.UNLOGIN);
    }


}
