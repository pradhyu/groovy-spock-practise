package com.test.practise

import org.spockframework.runtime.ConditionNotSatisfiedError
import spock.lang.Specification

/**
 * This is an ugly workaround for https://github.com/spockframework/spock/issues/922
 */
class PowerAssertionExtension extends Specification {
    static class PowerAssertionEnhancer {
        private static final String DASHED_LINE = "=" * 60

        static boolean specialAssert(String extraMessage, Closure assertion) {
            try {
                assertion()
            }
            catch (ConditionNotSatisfiedError e) {
                System.err.println DASHED_LINE
                System.err.println extraMessage
                System.err.println DASHED_LINE
                // needs trace turned on in gradle
                System.out.println DASHED_LINE
                System.out.println extraMessage
                System.out.println DASHED_LINE
                throw e
            }
            true
        }
    }

    def setupSpec() {
        getClass().mixin PowerAssertionEnhancer
    }

    def "Power-assert output for unparametrised 'assert'"() {
        expect:
        assert ("one".capitalize() * 3).toUpperCase() == "OneOneOne"
    }

    def "Power-assert output for implicit Spock-style 'assert'"() {
        expect:
        ("one".capitalize() * 3).toUpperCase() == "OneOneOne"
    }

    def "No power-assert output for parametrised 'assert'"() {
        expect:
        assert ("one".capitalize() * 3).toUpperCase() == "OneOneOne":
                "This is some additional info about the error"
    }

    def "Combine power-assert output with extra failure log output"() {
        expect:
        specialAssert "This is some additional info about the error", {
            assert ("one".capitalize() * 3).toUpperCase() == "OneOneOne"
        }
    }
}