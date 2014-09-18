package pl.warsjawa.marketing.domain

import groovy.transform.CompileStatic
import groovy.transform.Immutable
import groovy.transform.TypeChecked

@TypeChecked
@CompileStatic
class Person {
    final String firstName, lastName

    Person(String firstName, String lastName) {
        this.firstName = firstName
        this.lastName = lastName
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}