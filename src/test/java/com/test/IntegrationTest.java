package com.test;


import com.tusker.Application;
import com.tusker.DTO.Greeting;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * Creating a unit test to check the Spring Boot
 * Using TestRestTemplate : to get the Entity
 * Using ResponseEntry    : to get the URL response
 * Assertions.assertThat(): to check the response status, body.
 */

@RunWith(SpringRunner.class)
@SpringBootTest( classes = Application.class,webEnvironment = RANDOM_PORT) // classes : originally configure Spring-Boot
public class IntegrationTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void testName() throws Exception{
        // arrange

        /* act */
        ResponseEntity<Greeting> response = testRestTemplate.getForEntity( "/greeting",Greeting.class );

        /* assert */
        assertThat( response.getStatusCode() ).isEqualTo( HttpStatus.OK);
        //assertThat( response.getStatusCode() ).isEqualTo( HttpStatus.BAD_GATEWAY);
        assertThat( response.getBody().getContent() ).isEqualTo("Hello, World!");
//        assertThat( response.getBody().getContent() ).isEqualTo("Hello, World");

    }


    /**
     * Runner for the test to catch Exception
     * Prints the failure
     * Prints whether the result was successful or not.
     */
    public static class IntegrationTestRunner{
        public static void main(String[] args) {
            Result result = JUnitCore.runClasses(IntegrationTest.class);
            for( Failure failure : result.getFailures() ){
                System.out.println( "\n\n" + failure.toString() );
            }
            System.out.println( result.wasSuccessful() );
        }
    }
}
