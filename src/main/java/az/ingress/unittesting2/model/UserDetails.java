package az.ingress.unittesting2.model;

import lombok.*;

/**
 * @author caci
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {
    private Long id;
    private String name;
    private Short age;
}
