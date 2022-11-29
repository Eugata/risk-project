package com.kkcloud.risk.exception;

import com.kkcloud.risk.vo.ParentCommonStatusCode;
import com.kkcloud.risk.vo.ResponseHelper;
import com.kkcloud.risk.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.List;

@ResponseBody
@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public ResponseVO<Object> handleCommonException(MethodArgumentNotValidException ex) {
        log.error("----"+ex.getMessage(), ex);
        BindingResult bindingResult = ex.getBindingResult();
        List<ObjectError> errors =  bindingResult.getAllErrors();
        StringBuilder sb = new StringBuilder();
        if(errors != null) {
            for(ObjectError error: errors) {
                sb.append(error.getDefaultMessage());
            }
        }
        ResponseVO result = new ResponseVO(ParentCommonStatusCode.NOT_ACCEPTABLE.getCode(), sb.toString(), null);
        return result;
    }
}
