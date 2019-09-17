package com.jobsity.bowling.controller;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class CustomPrintStream extends PrintStream {

    public List<String> lines = new ArrayList<>();
    private StringBuilder tempLine = null;

    public List<String> getLines() {
        return lines;
    }

    public CustomPrintStream(OutputStream out) {
        super(out);
    }

    @Override
    public void println(String str) {

//
////        tempLine.append(str);
////        lines.add(tempLine.toString());
//        if (str.contains("")) {
//            lines.add(tempLine.toString());
//        }
//        tempLine = null;
//
        super.println(str);
        if (tempLine == null) {

            tempLine = new StringBuilder();

        } else {

            lines.add(tempLine.toString());
            tempLine = null;

        }

    }


    @Override
    public void print(String str) {

        super.print(str);

        if (tempLine == null) {
            tempLine = new StringBuilder();
        }
        tempLine.append(str);

    }

    @Override
    public void println() {

        println("");
    }
}
