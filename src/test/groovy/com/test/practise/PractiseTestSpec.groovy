package com.test.practise

import spock.lang.Issue
import spock.lang.Specification
import spock.lang.Timeout
import spock.lang.Title
import spock.lang.Unroll

import java.util.concurrent.TimeUnit

@Title("Spock tests to test the spock extensions to spice up the unit-tests")
class PractiseTestSpec extends Specification {

    // time out extension annotation
    @Timeout(value=1,unit=TimeUnit.MICROSECONDS)
    @Issue("http//atlasian.com/RUL-blah-story-num")
    @Unroll
    void "Timeout test, forced failure with 1 milliseconds" () {
        given: "two variables x and y ->#what"
        def x = 1
        def y = 1
        when: "we use addition function with ->#what"
        def k = x + y
        then: " we should get sum=2"
        k == 2
        where:" # varname in description only works for variables defined in where"
        what << ["what","the", "hell"]
    }

    @Unroll
    def "maximum of #a and #b is #c"(int a, int b, int c) {
        expect: "Math.max(#a #b) should the function pics maximum number #c"
        Math.max(a, b) == c

        where:"#a and #b is passed to Math.max"
        a | b | c
        1 | 3 | 3
        7 | 4 | 4
        0 | 0 | 0
    }

    @Unroll
    def "maximum of two numbers when #testLabel"(String testLabel, int a, int b, int c) {
        expect:
        Math.max(a, b) == c

        where:
        testLabel                 | a | b | c
        "first number is bigger"  | 4 | 1 | 4
        "second number is bigger" | 0 | 5 | 5
        "numbers are equal"       | 3 | 3 | 3
    }

}
