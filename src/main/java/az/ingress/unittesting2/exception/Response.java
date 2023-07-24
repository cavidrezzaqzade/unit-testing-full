package az.ingress.unittesting2.exception;

import org.springframework.http.HttpStatus;

public interface Response {
    String getKey();
    String getMessage();
    HttpStatus getHttpStatus();
}
