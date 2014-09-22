package pl.warsjawa.marketing.domain

import groovy.transform.CompileStatic
import groovy.transform.Immutable
import groovy.transform.ToString
import groovy.transform.TypeChecked

@TypeChecked
@ToString
class Person {
    final String firstName, lastName

    Person(String firstName, String lastName) {
        this.firstName = firstName
        this.lastName = lastName
    }
}