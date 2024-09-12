package com.project.vinpong.apiPayload.exception.handler;

import com.project.vinpong.apiPayload.code.BaseErrorCode;
import com.project.vinpong.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {
    public MemberHandler(BaseErrorCode baseErrorCode) {

        super(baseErrorCode);
    }
}
