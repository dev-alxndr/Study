package io.alxndr.demoexceptionhandler;

/**
 * @author Alexander
 * @date 2020-11-12
 **/
public enum ErrorCode {

    INDEX_NOT_FOUND(1001, "인덱스가 존재하지 않습니다."),
    BOARD_NOT_FOUND(1002, "게시글을 찾을 수 없습니다.");

    private int errorCode;
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    ErrorCode(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
