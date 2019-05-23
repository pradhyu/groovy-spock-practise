package com.test.practise

import spock.lang.Specification
import spock.util.mop.Use

// run all test in this file using gradle filter
//./gradlew test --tests *Use*
class UseOnMethods extends Specification {
    // can be applied to a fixture method
    @Use(StringExtensions)
    def setupSpec() {
        assert "foo".duplicate() == "foofoo"
    }

    @Use(StringExtensions)
    def "can be applied to a feature method"() {
        expect:
        "foo".duplicate() == "foofoo"
    }

    @Use([StringExtensions, IntegerExtensions])
    def "can use multiple categories"() {
        expect:
        3.mul(4) == 12
        "foo".duplicate() == "foofoo"
    }

    def "has no effect when applied to a helper method"() {
        when:
        helper()

        then:
        thrown(MissingMethodException)
    }

    @Use(StringExtensions)
    def helper() {
        "foo".duplicate()
    }

    def "has no effect on remaining feature methods"() {
        when:
        "foo".duplicate()

        then:
        thrown(MissingMethodException)
    }

    // has no effect on remaining fixture methods
    def setup() {
        try {
            "foo".duplicate()
            assert false
        } catch (MissingMethodException expected) {
        }
    }
}

@Use(StringExtensions)
class UseOnClasses extends Specification {
    // affects fixture methods
    def setupSpec() {
        assert "foo".duplicate() == "foofoo"
    }

    def "affects feature methods"() {
        expect:
        "foo".duplicate() == "foofoo"
    }

    def "affects helper methods"() {
        when:
        helper()

        then:
        noExceptionThrown()
    }

    def helper() {
        assert "foo".duplicate() == "foofoo"
    }

    @Use(IntegerExtensions)
    def "can be combined with method-level category"() {
        expect:
        3.mul(4) == 12
        "foo".duplicate() == "foofoo"
    }
}

class StringExtensions {
    static String duplicate(String str) { str * 2 }
}

class IntegerExtensions {
    static mul(Integer n, Integer m) { n * m }
}