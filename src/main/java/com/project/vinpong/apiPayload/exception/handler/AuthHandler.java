package com.project.vinpong.apiPayload.exception.handler;

import com.project.vinpong.apiPayload.code.BaseErrorCode;
import com.project.vinpong.apiPayload.exception.GeneralException;

public class AuthHandler extends GeneralException {
    public AuthHandler(BaseErrorCode code) {
        super(code);
    }
}
