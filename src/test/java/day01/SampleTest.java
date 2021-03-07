package day01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//import static org.junit.jupiter.api.Assertions.Assertions;
import static org.junit.jupiter.api.Assertions.*;

public class SampleTest {

    @Test // This annotation comes from -> Test org.junit.jupiter.api
    public void calculatorTest(){

        System.out.println("Hello World");
        // All assertions come from -> Test org.junit.jupiter.api

// We import ->  import static org.junit.jupiter.api.Assertions.*; not all time time Assertion beginning of the assertions
        // assert 4+5 is 9
        assertEquals(9,4+5);

        // how do we add error message if the assertion fail
        //assertEquals(10, 5+4, "Hey wrong result!");


    }
    // you can add the display name for your test instead of the method name
    // providing the custom name for the test
    @DisplayName("I'm testing the name")
    @Test
    public void  nameTest(){

        // write a simple assertion
        // concatenate your first name and last name
        // and make assertion it's equal to your full name

        String firstName="Hello";
        String lastName="World";
        assertEquals("Hello World", firstName+" "+lastName);

    }

}
