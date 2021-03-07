package day01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

// Hamcrest library is a assertion library
// to aim make the test more human readable
// using lots of human readable matchers like something is (somethingelse)
// Most importantly reassured use hamcrest matcher
   // when we chain multiple rest assured methods
public class Practice2_Hamcrest {

    @Test
    public void test1(){

        // assert 5+4 is 9
        int num1=5;
        int num2=4;

        // we need these 2 import for this work
        // - import static org.hamcrest.MatcherAssert.assertThat;
        // - import static org.hamcrest.Matchers.*;
        // Hamcrest already come with RestAssured dependency


        // hamcrest library use the assertThat method for all assertions
        // hamcrest use to built in matchers to do assertion
        // couple common ones are:
            // is(some value)
            // equalTo(some value)
           // or optionally is (equalTo(some value))
        assertThat(num1+num2, is(9)) ;
        assertThat(num1+num2, equalTo(9)) ;
        assertThat(num1+num2, is(equalTo(9))) ;

        // not(value)
        // is (not(some value))
        // not(equalTo(11)))
        assertThat(num1+num2, not(11)) ;
        assertThat(num1+num2, is(not(11)));

        // save your first name and last name intro 2 variables
        // test the concatenation result using hamcrest matcher
        String firstName="Hello ";
        String lastName="World";
        assertThat(firstName+lastName,is("Hello World"));
        assertThat(firstName+lastName,equalTo("Hello World"));
        assertThat(firstName+lastName,is(equalTo("Hello World")));

        // String matchers
        // equalToIgnoreCase
        // equalToCompressingWhiteSpace
        // containsString, endsWith, startsWith - test string matching

        // how to check in caseInsensitive manner
        assertThat(firstName, equalToIgnoringCase("hello "));
        // how to ignore all whitespaces
        assertThat(firstName,equalToCompressingWhiteSpace("Hello"));
        assertThat(firstName+lastName,endsWith("World"));


    }

    @DisplayName("Support for collection")
    @Test
    public void test2(){

        List<Integer>numList= Arrays.asList(11,44,3,55,88,5);
        // checking the list size is 6
        assertThat(numList, hasSize(6));
        // checking the list contains 11
        assertThat(numList, hasItem(11));

        // contains method works as equals in here
        // checking the list contains and items with order : 11,44,3,55,88,5
        assertThat(numList, contains(11,44,3,55,88,5));

        // checking the list contains more that items with order : 11,44,3,55,88,5
        assertThat(numList, containsInAnyOrder(11,44,3,55,88,5));


    }
}
