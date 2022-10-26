package com.boost.exception;

import lombok.Getter;

@Getter
public class ElasticServiceException extends RuntimeException{
    private final ErrorType errorType;

    public ElasticServiceException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public ElasticServiceException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }
}