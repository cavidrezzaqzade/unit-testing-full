package az.ingress.unittesting2.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;
import java.util.Map;

@Slf4j
public class ApplicationException extends RuntimeException {

    private final Response response;
    private final Map<String, Object> messageArguments;

    public Response getErrorResponse() {
        return response;
    }

    public ApplicationException(Response errorResponse, Map<String, Object> messageArguments) {
        this.response = errorResponse;
        this.messageArguments = messageArguments;
    }

    @Override
    public String getMessage() {
        return messageArguments.isEmpty() ? response.getMessage() :
                StringSubstitutor.replace(response.getMessage(), messageArguments, "{", "}");
    }

    public String getLocalizedMessage(Locale locale, MessageSource messageSource) {
        try {
            String localizedMessage = messageSource.getMessage(response.getKey(), new Object[]{}, locale);
            return messageArguments.isEmpty() ? localizedMessage :
                    StringSubstitutor.replace(localizedMessage, messageArguments, "{", "}");
        } catch (NoSuchMessageException exception) {
            log.warn("Please consider adding localized message for key {} and locale {}",
                    response.getKey(), locale);
        }
        return getMessage();
    }
}
