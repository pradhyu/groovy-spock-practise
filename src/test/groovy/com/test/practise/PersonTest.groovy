package com.test.practise

import spock.lang.*

class PersonTest extends Specification {
    @Subject
    Person person = new Person(metaClass: null, firstName: "firstName", lastName: "lastName", age: 0)

    def "test print"() {
        when:
        Object result = person.print()

        then:
        result == null
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme