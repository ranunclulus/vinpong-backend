package com.project.vinpong.apiPayload.code.status;

import com.project.vinpong.apiPayload.code.BaseErrorCode;
import com.project.vinpong.apiPayload.code.ErrorReasonDto;

public enum ErrorStatus implements BaseErrorCode {
    ;

    @Override
    public ErrorReasonDto getReason() {
        return null;
    }

    @Override
    public ErrorReasonDto getReasonHttpStatus() {
        return null;
    }
}
