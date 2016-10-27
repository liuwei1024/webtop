/**
 * Copyright 2016 (c) Shanghai TY Technology Co.,Ltd. All Rights Reserved.
 */
package com.docworks.webtop.web.controller;

import com.docworks.webtop.bean.dto.FieldErrorDto;
import com.docworks.webtop.bean.dto.ResponseDto;
import com.docworks.webtop.bean.enums.message.HttpMessageCode;
import com.docworks.webtop.exception.BadRequestException;
import com.docworks.webtop.exception.BusinessException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuwei
 * @version 1.0
 */
@RestControllerAdvice
public class ControllerAdvice {

    /**
     * Log4j logger
     */
    public static final Logger logger = Logger.getLogger(ControllerAdvice.class);

    @Autowired
    private MessageSource messageSource;

    /**
     * 表单数据校验异常处理
     *
     * @param exception BindException
     * @param request       WebRequest
     * @return ResponseEntity<ResponseDto>
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseDto> bindException(BindException exception, WebRequest request) {
        logger.error(exception.getMessage(), exception);

        BindingResult bindingResult = exception.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        List<FieldErrorDto> fieldErrorDtos = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            fieldErrorDtos.add(new FieldErrorDto(fieldError.getField(), fieldError.getDefaultMessage()));
        }

        // 格式化错误消息
        String messageCode = HttpMessageCode.HTTP_BAD_REQUEST.toString();
        String message = messageSource.getMessage(messageCode, null, request.getLocale());
        ResponseDto responseDto = new ResponseDto(messageCode, message, fieldErrorDtos);

        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    /**
     * 处理BadRequestException异常
     *
     * @param exception BadRequestException
     * @param request   WebRequest
     * @return ResponseEntity<ResponseDto>
     */
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseDto> badRequestException(BadRequestException exception, WebRequest request) {
        logger.error(exception.getMessage(), exception);

        String message = messageSource.getMessage(exception.getMessageCode(), exception.getParams(), request.getLocale());
        ResponseDto responseDto = new ResponseDto(exception.getMessageCode(), message, exception.getMessage());
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    /**
     * 处理BusinessException异常
     *
     * @param exception BusinessException
     * @param request   WebRequest
     * @return ResponseEntity<ResponseDto>
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseDto> businessException(BusinessException exception, WebRequest request) {
        logger.error(exception.getMessage(), exception);

        String message = messageSource.getMessage(exception.getMessageCode(), exception.getParams(), request.getLocale());
        ResponseDto responseDto = new ResponseDto(exception.getMessageCode(), message, exception.getMessage());
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    /**
     * 服务器内部错误异常处理
     *
     * @param exception Exception
     * @param request   WebRequest
     * @return ResponseEntity<ResponseDto>
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseDto> serverError(Exception exception, WebRequest request) {
        logger.error(exception.getMessage(), exception);

        String messageCode = HttpMessageCode.HTTP_INTERNAL_SERVER_ERROR.value();
        String message = messageSource.getMessage(messageCode, null, request.getLocale());
        ResponseDto responseDto = new ResponseDto(messageCode, message, exception.getMessage());
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }
}
