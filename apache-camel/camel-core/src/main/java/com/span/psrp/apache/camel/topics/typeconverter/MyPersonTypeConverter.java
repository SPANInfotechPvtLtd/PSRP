

package com.span.psrp.apache.camel.topics.typeconverter;

import org.apache.camel.Converter;

@Converter
public final class MyPersonTypeConverter {
    /**
     * Utility classes should not have a public constructor.
     */
    private MyPersonTypeConverter() {
    }

    /**
     * Converts a String in the format of "firstName|lastName" to a {@link MyPerson}.
     */
    @Converter
    public static MyPerson convertStringToMyPerson(String str) {
        final int index = str.indexOf("|");
        if (index > 0) {
            final MyPerson person = new MyPerson();

            person.setFirstName(str.substring(0, index));
            person.setLastName(str.substring(index + 1)); // skip the '|' at index
            System.out.println(person.getFirstName());
            System.out.println(person.getLastName());
            return person;
        }
        System.out.println("String must be in format of '<firstName>|<lastName>'");
        throw new IllegalArgumentException("String must be in format of '<firstName>|<lastName>'");
    }
}
