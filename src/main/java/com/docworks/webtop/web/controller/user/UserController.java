/**
 * Copyright 2016 (c) Shanghai TY Technology Co.,Ltd. All Rights Reserved.
 */
package com.docworks.webtop.web.controller.user;

import com.documentum.fc.client.IDfSession;
import com.docworks.webtop.bean.domain.User;
import com.docworks.webtop.bean.dto.ResponseDto;
import com.docworks.webtop.bean.vo.PageVo;
import com.docworks.webtop.service.UserService;
import com.docworks.webtop.web.method.annotation.DfSession;
import com.docworks.webtop.bean.dto.PageDto;
import com.docworks.webtop.bean.enums.message.HttpMessageCode;
import com.docworks.webtop.core.convert.DqlCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Locale;

/**
 * @author liuwei
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/list")
    public ResponseEntity<ResponseDto> findAll(PageVo pageVo, @DfSession IDfSession dfSession, Locale locale) {
        PageDto<User> pageDto = userService.findAll(dfSession, new DqlCondition(pageVo));

        String message = messageSource.getMessage(HttpMessageCode.HTTP_OK.value(), null, locale);
        ResponseDto responseDto = new ResponseDto(HttpMessageCode.HTTP_OK.value(), message, pageDto);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> create(@Valid User user, @DfSession IDfSession dfSession, Locale locale) {



        String message = messageSource.getMessage(HttpMessageCode.HTTP_OK.value(), null, locale);
        ResponseDto responseDto = new ResponseDto(HttpMessageCode.HTTP_OK.value(), message, null);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }
}
