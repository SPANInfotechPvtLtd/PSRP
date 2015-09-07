package com.span.psrp.apache.camel.topics.typeconverter;

public class MyPerson {
    private String firstName;
    private String lastName;

    public MyPerson() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyPerson myPerson = (MyPerson) o;

        if (firstName != null ? !firstName.equals(myPerson.firstName) : myPerson.firstName != null) return false;
        if (lastName != null ? !lastName.equals(myPerson.lastName) : myPerson.lastName != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "MyPerson{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
    }
}
