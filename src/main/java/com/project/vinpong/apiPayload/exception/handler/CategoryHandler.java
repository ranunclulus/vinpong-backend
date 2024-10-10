package com.project.vinpong.apiPayload.exception.handler;

import com.project.vinpong.apiPayload.code.BaseErrorCode;
import com.project.vinpong.apiPayload.exception.GeneralException;

public class CategoryHandler extends GeneralException {
    public CategoryHandler(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
