package com.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> SqlExceptionHandler(SQLIntegrityConstraintViolationException e){
        log.error(e.getMessage());
        log.error(e.getMessage());
        if(e.getMessage().contains("Duplicate entry")){
            String s = e.getMessage().split(" ")[2];
            return R.error(s+"已存在");
        }
        return R.error("未知错误");
    }

    @ExceptionHandler(CustomExcept.class)
    public R<String> customExcept(CustomExcept e){
        log.error(e.getMessage());
        return R.error(e.getMessage());
    }

}
