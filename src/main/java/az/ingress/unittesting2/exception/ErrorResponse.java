package az.ingress.unittesting2.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author caci
 */

@Getter
@Setter
@Builder
public class ErrorResponse {
    private String message;
    private List<ConstraintsViolationError> violationErrors;
}
