import groovy.transform.Canonical

@Canonical
class Person {
    String firstName
    String lastName
    int age

    String getFullName(){
        return firstName + " " + lastName
    }
}
