package com.jobsity.bowling.controller;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Filter;

import static org.junit.Assert.assertEquals;

public class ValidatorTest {

    @Test
    public void tokenValidationTest() {

        String jeff = "Jeff";
        String[] tokens = {jeff};
        testTokens(tokens, "Invalid number of tokens", tokens);

        String line = " ";
        tokens = new String[]{line, "10"};
        testTokens(tokens, "Invalid name", tokens);

        tokens = new String[]{jeff, "A"};
        testTokens(tokens, "Invalid number of pins", tokens);

        tokens = new String[]{jeff, "-3"};
        testTokens(tokens, "Number of pins must be positive", tokens);

        tokens = new String[]{jeff, "13"};
        testTokens(tokens, "Number of pins must less than 10", tokens);

    }

    private void testTokens(String[] tokens, String msg, String[] line) {

        List<String> msgs = new ArrayList<>();
        Filter filter = record -> {

            msgs.add(record.getMessage());
            return true;

        };
        TokenValidator.LOGGER.setFilter(filter);
        try {

            TokenValidator.areTokensValid(tokens, 0);

        } catch (RuntimeException e) { }

        assertEquals("com.jobsity.bowling.model.FileValidatorException "+msg+", in line: 0 - "+
                        Arrays.toString(line)+"", msgs.get(0));

    }

}
