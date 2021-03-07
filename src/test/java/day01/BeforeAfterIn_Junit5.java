package day01;

import org.junit.jupiter.api.*;

public class BeforeAfterIn_Junit5 {
    // This method will run only once before the entire test
    // same idea as @BeforeClass we learned previously
    // these are Junit5 Specific annotation
    @BeforeAll
    public static void setUp(){
        System.out.println("This run before All");
    }

    // same idea as @BeforeMethod we learned previously
    // these are Junit5 Specific annotation for same idea
    @BeforeEach
    public void beforeEachTask(){
        System.out.println("Running before the test");
    }

    @Test
    public void test1(){
        System.out.println("Test1 is running");
    }

    //@Disabled// same idea as @ignore we learned before
    @Test
    public void test2(){
        System.out.println("Test2 is running");
    }

    // same idea as @AfterMethod we learned previously
    @AfterEach
    public void afterEachTest(){
        System.out.println("Running after each test");
    }

    //this method run only once after all the test
    // some idea as @AfterClass annotation we learned previously
    // it has to be static
    @AfterAll
    public static void tearDown(){
        System.out.println("This run all the way at the end");
    }


}
