package com.jobsity.bowling.controller;

import java.util.Arrays;

public class Validator {

    public static boolean areTokensValid(String[] tokens, int i) throws RuntimeException {

        try {

            if (tokens.length <= 1) {
                throwError(tokens, i, "Invalid number of tokens");
            }

            String playerName = tokens[0];
            if (playerName.trim().equals(""))
                throwError(tokens, i, "Invalid name");

            String playerPins = tokens[1];
            if (!playerPins.equals("F")) {
                try {

                    int integer = Integer.valueOf(playerPins);
                    if (integer < 0)
                        throwError(tokens, i, "Number of pins must be positive");
                    if (integer > 10)
                        throwError(tokens, i, "Number of pins must less than 10");

                } catch (NumberFormatException e) {
                    throwError(tokens, i, "Invalid number of pins");
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    public static void throwError(String[] tokens, int i, String msg) throws Exception {

        throw new Exception(msg + ", in line: " + i + " - "+ Arrays.toString(tokens));
    }

}
