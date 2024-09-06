package com.project.vinpong.apiPayload.exception.handler;

import com.project.vinpong.apiPayload.code.BaseErrorCode;
import com.project.vinpong.apiPayload.exception.GeneralException;

public class StyleHandler extends GeneralException {
    public StyleHandler(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
