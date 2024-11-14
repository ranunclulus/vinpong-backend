package com.project.vinpong.apiPayload.code.status;

import com.project.vinpong.apiPayload.code.BaseErrorCode;
import com.project.vinpong.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON5001", "서버 에러, 관리자에게 문의 바랍니다."),
    PARSING_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON5002", "데이터 파싱에 실패했습니다"),
    STYLE_NOT_FOUND(HttpStatus.BAD_REQUEST, "STYLE4001", "스타일이 없습니다."),
    CATEGORY_NOT_FOUND(HttpStatus.BAD_REQUEST, "CATEGORY4001", "카테고리가 없습니다."),

    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "멤버가 없습니다."),
    ALREADY_EXIST_MEMBERNAME(HttpStatus.BAD_REQUEST, "MEMBER4002", "이미 존재하는 멤버 이름입니다."),
    ALREADY_EXIST_EMAIL(HttpStatus.BAD_REQUEST, "MEMBER4003", "이미 존재하는 멤버 이메일입니다."),
    PASSWORD_NOT_MATCH(HttpStatus.BAD_REQUEST, "MEMBER4004", "비밀번호가 일치하지 않습니다."),

    ACCESSTOKEN_INVALID(HttpStatus.FORBIDDEN, "JWT4001", "유효하지 않은 토큰입니다."),
    ATHENTICATION_NOT_FOUND(HttpStatus.FORBIDDEN, "JWT4002", "인증 정보를 찾을 수 없습니다."),
    EXPIRED_ACCESSTOKEN(HttpStatus.FORBIDDEN, "JWT4003", "만료된 토큰입니다."),
    UNSUPPORTED_ACCESSTOKEN(HttpStatus.FORBIDDEN, "JWT4003", "만료된 토큰입니다."),

    IMAGE_UPLOAD_FAIL(HttpStatus.BAD_REQUEST, "S34001", "S3 버킷에 이미지 등록을 실패했습니다."),

    ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "ITEM4001", "아이템이 없습니다"),
    SELLER_NOT_MATCHED(HttpStatus.BAD_REQUEST, "ITEM4002", "아이템의 판매자가 아닙니다");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
