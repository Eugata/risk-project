package com.kkcloud.risk.exception;

import com.kkcloud.risk.vo.ResponseHelper;
import com.kkcloud.risk.vo.ResponseVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ResponseBody
@ControllerAdvice
public class FileUploadExceptionAdvice {
    @SuppressWarnings("rawtypes")
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseVO<Object> handleMaxSizeException(MaxUploadSizeExceededException exc)
    {
        exc.printStackTrace();
        return ResponseHelper.fileSize(exc);
    }
}