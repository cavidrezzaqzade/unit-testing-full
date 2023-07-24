package az.ingress.unittesting2.exception;

import org.springframework.http.HttpStatus;

public enum Errors implements Response {
    DATA_NOT_FOUND( "data.not.found", HttpStatus.NOT_FOUND, "bu id-li '{id}' məlumat tapılmadı"),
    INTERNAL_SERVER_ERROR( "unexpected.error", HttpStatus.INTERNAL_SERVER_ERROR, "daxili server xətası");

    final String key;
    final HttpStatus httpStatus;
    final String message;//default message

    Errors(String key, HttpStatus httpStatus, String message) {
        this.key = key;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public String getKey() {
        return key;
    }
    @Override
    public String getMessage() {
        return message;
    }
    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}