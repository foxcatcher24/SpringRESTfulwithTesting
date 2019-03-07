package com.test;

import com.tusker.Application;
import com.tusker.controller.GreetingController;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Testing web MVC with MockMVC
 * Using MockMVC : to get the controller URL
 * and perform a check the status and JSON response body.
 */
@ContextConfiguration( classes = Application.class )
@RunWith( SpringRunner.class )
@WebMvcTest( GreetingController.class )
public class GreetingControllerTest {

    // Declare variables here...
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getGreeting() throws Exception{

        // To-Do:... {write the test code here}
        mockMvc.perform( MockMvcRequestBuilders.get("/greeting") )
                .andExpect( status().isOk() )
                .andExpect( jsonPath("content").value("Hello, World!") );

//      mockMvc.perform( MockMvcRequestBuilders.get("/greeting") )
//              .andExpect( status().isOk() )
//              .andExpect( jsonPath("content").value("Hello, World") );
    }

    /**
     * Runner for the test to catch Exception
     * Prints the failure
     * Prints whether the result was successful or not.
     */
    public static class IntegrationTestRunner{
        public static void main( String[] args ) {
            Result result = JUnitCore.runClasses( GreetingControllerTest.class );
            for( Failure failure : result.getFailures() ){
                System.out.println( "\n\n" + failure.toString() );
            }
            System.out.println( result.wasSuccessful() );
        }
    }

}
