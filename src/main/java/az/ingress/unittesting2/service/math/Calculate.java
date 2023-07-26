package az.ingress.unittesting2.service.math;

import org.springframework.stereotype.Service;

/**
 * @author caci
 */

@Service
public class Calculate {

    public static int sum(int a, int b) {
        return a + b;
    }

    public static void unrollCondition(int number){
        if(number > 3){
            test();
        }
    }

    public static void test(){
        System.out.println("test");
    }

}
