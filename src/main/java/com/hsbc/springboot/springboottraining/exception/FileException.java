package com.hsbc.springboot.springboottraining.exception;

import com.hsbc.springboot.springboottraining.enums.ResultEnum;
import lombok.Data;

@Data
public class FileException extends RuntimeException{

    private Integer code;

    public FileException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public FileException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}