package com.jobsity.bowling.controller;

import com.jobsity.bowling.model.FileValidatorException;

import java.util.Arrays;
import java.util.logging.Logger;

public class TokenValidator {

    public static final Logger LOGGER = Logger.getLogger(TokenValidator.class.getName());

    public static boolean areTokensValid(String[] tokens, int index) throws RuntimeException {

        try {

            if (tokens.length <= 1) {

                throwError(tokens, index, "Invalid number of tokens");
            }

            String playerName = tokens[0];
            if (playerName.trim().equals("")) {

                throwError(tokens, index, "Invalid name");
            }

            String playerPins = tokens[1];
            if (!playerPins.equals("F")) {

                try {

                    int integer = Integer.valueOf(playerPins);
                    if (integer < 0) {

                        throwError(tokens, index, "Number of pins must be positive");
                    }
                    if (integer > 10) {

                        throwError(tokens, index, "Number of pins must less than 10");
                    }

                } catch (NumberFormatException e) {

                    throwError(tokens, index, "Invalid number of pins");
                }
            }

        } catch (Exception e) {

            LOGGER.severe(e.getClass().getName() + e.getMessage());
            throw new RuntimeException(e);
        }

        return true;
    }

    public static void throwError(String[] tokens, int i, String msg) throws Exception {

        throw new FileValidatorException(" "+msg + ", in line: " + i + " - "+ Arrays.toString(tokens));
    }

}
