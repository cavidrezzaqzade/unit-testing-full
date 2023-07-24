package az.ingress.unittesting2.util;

import lombok.experimental.UtilityClass;
import org.springframework.context.i18n.LocaleContextHolder;
import java.util.ResourceBundle;

/**
 * @author caci
 */

@UtilityClass
public class LocalizationUtil {

    public static String getLocalizedMessageByKey(String bundleName, String key){
        var locale = LocaleContextHolder.getLocale();
        return ResourceBundle.getBundle(bundleName, locale).getString(key);
    }

    public static String getLocalizedMessageByStatusCode(String bundleName, Integer statusCode){
        var locale = LocaleContextHolder.getLocale();
        var resourceBundle = ResourceBundle.getBundle(bundleName, locale);

        switch (statusCode){
            case 404 -> {
                return resourceBundle.getString("not.found.exception");
            }
            case 403 -> {
                return resourceBundle.getString("forbidden.exception");
            }
            case 402 -> {
                return resourceBundle.getString("payment.required.exception");
            }
        }
        return resourceBundle.getString("unexpected.exception");
    }
}
