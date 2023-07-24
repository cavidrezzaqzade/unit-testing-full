package az.ingress.unittesting2.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;
import java.util.stream.Collectors;

import static az.ingress.unittesting2.util.LocalizationUtil.getLocalizedMessageByKey;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ErrorHandler extends DefaultErrorAttributes {

    private final MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGeneralException(Exception ex) {
        log.error("General Exception: ", ex);

        var m = getLocalizedMessageByKey("errors", "unexpected.exception");
//        var message = Errors.INTERNAL_SERVER_ERROR.getMessage();

        return ErrorResponse.builder()
                .message(m)
                .build();
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> handle(ApplicationException ex) {
        log.error(" ApplicationException: ", ex);

        var message = ex.getLocalizedMessage(LocaleContextHolder.getLocale(), messageSource);

        return new ResponseEntity<>(ErrorResponse.builder()
                .message(message)
                .build(), ex.getErrorResponse().getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public final ErrorResponse handle(MethodArgumentNotValidException ex) {
        log.error(" MethodArgumentNotValidException: ", ex);

        List<ConstraintsViolationError> validationErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ConstraintsViolationError(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());

        String localizedMessage = messageSource.getMessage(ex.getClass().getName().concat(".message"),
                new Object[]{}, LocaleContextHolder.getLocale());

        return ErrorResponse.builder()
                .message(localizedMessage)
                .violationErrors(validationErrors)
                .build();
    }

}

