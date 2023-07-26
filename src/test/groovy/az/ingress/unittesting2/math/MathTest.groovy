package az.ingress.unittesting2.math

import az.ingress.unittesting2.service.math.Calculate

import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author caci
 */

class MathTest extends Specification {

    def "TestSum success case"() {
        given:
        def a = 3
        def b = 6

        when:
        def actual = Calculate.sum(3, 6)

        then:
        actual == 9
    }

    def "TestSum error case"() {
        given:
        def a = 2
        def b = 6

        when:
        def actual = Calculate.sum(3, 6)

        then:
        actual == 9
    }

    @Unroll
    def "TestSum unroll success"() {
        given:
        def a = firstParam
        def b = secondParam

        when:
        def actual = Calculate.sum(firstParam, secondParam)

        then:
        actual == sum

        where:
        firstParam | secondParam | sum
        2          | 3           | 5
        3          | 6           | 9
        5          | 7           | 12
    }

    @Unroll
    def "TestSum under condition success case"() {
        given:
        def a = param

        when:
        Calculate.unrollCondition(param)

        then:
        callsCount * Calculate.test()

        where:
        param | callsCount
        1     | 0
        20    | 1
        3     | 0
        4     | 1
        2     | 0
    }

}
