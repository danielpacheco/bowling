package com.jobsity.bowling.controller;

import com.jobsity.bowling.Bowling;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;

import static org.junit.Assert.assertEquals;

public class ScoreObtainerIntegrationTest {

    @Test
    public void testBowlingWithPDFValuesTest() {

        Bowling bowling = new Bowling();
        CustomPrintStream outputStream = new CustomPrintStream(System.out);
        System.setOut(outputStream);
        bowling.main(new String[]{"playsInPDF.txt"});

        testOutput(outputStream);

    }

    private void testOutput(CustomPrintStream outputStream) {
        List<String> lines = outputStream.getLines();
        assertEquals("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10", lines.get(0));
        assertEquals("Jeff", lines.get(1));
        assertEquals("Pinfalls\tX\t\t7\t/\t9\t0\tX\t\t0\t8\t8\t/\tF\t6\tX\t\tX\t\tX\t\t8\t1\t", lines.get(2));
        assertEquals("Score\t\t20\t\t39\t\t48\t\t66\t\t74\t\t84\t\t90\t\t120\t\t148\t\t167\t\t176\t\t",
                lines.get(3));
        assertEquals("John", lines.get(4));
        assertEquals("Pinfalls\t3\t/\t6\t3\tX\t\t8\t1\tX\t\tX\t\t9\t0\t7\t/\t4\t4\tX\t\t9\t0\t",
                lines.get(5));
        assertEquals("Score\t\t16\t\t25\t\t44\t\t53\t\t82\t\t101\t\t110\t\t124\t\t132\t\t151\t\t160\t\t",
                lines.get(6));
    }

    @Test
    public void testBowlingPlaysMoreThan11Test() {

        Bowling bowling = new Bowling();
        CustomPrintStream outputStream = new CustomPrintStream(System.out);
        System.setOut(outputStream);
        bowling.main(new String[]{"playsMoreThan11.txt"});

        testOutput(outputStream);

    }

    @Test
    public void testBowlingPlaysWithFoulTest() {

        Bowling bowling = new Bowling();
        CustomPrintStream outputStream = new CustomPrintStream(System.out);
        System.setOut(outputStream);
        bowling.main(new String[]{"playsWithFoul.txt"});

        List<String> lines = outputStream.getLines();
        assertEquals("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10", lines.get(0));
        assertEquals("Jeff", lines.get(1));
        assertEquals("Pinfalls\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\t", lines.get(2));
        assertEquals("Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t", lines.get(3));
        assertEquals("John", lines.get(4));
        assertEquals("Pinfalls\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\t", lines.get(5));
        assertEquals("Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t", lines.get(6));

    }

    @Test
    public void testBowlingPlaysWithFoul2Test() {

        Bowling bowling = new Bowling();
        CustomPrintStream outputStream = new CustomPrintStream(System.out);
        System.setOut(outputStream);
        bowling.main(new String[]{"playsWithFoul2.txt"});

        List<String> lines = outputStream.getLines();
        assertEquals("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10", lines.get(0));
        assertEquals("Jeff", lines.get(1));
        assertEquals("Pinfalls\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\t", lines.get(2));
        assertEquals("Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t", lines.get(3));
        assertEquals("John", lines.get(4));
        assertEquals("Pinfalls\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\t", lines.get(5));
        assertEquals("Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t", lines.get(6));

    }

    @Test
    public void testBowlingPlaysWithMoreThan10ScoreTest() {

        List<String> msgs = new ArrayList<>();
        Filter filter = record -> {

            msgs.add(record.getMessage());
            return true;

        };
        MapScoreObtainer.LOGGER.setFilter(filter);
        try {

            Bowling bowling = new Bowling();
            bowling.main(new String[]{"playsWithMoreThan10Score.txt"});

        } catch (RuntimeException e) { }
        assertEquals("com.jobsity.bowling.model.ScoreMoreThanTenException for player: John, on frame: 1",
                msgs.get(0));
    }

    @Test
    public void testBowlingPlaysWithStrikesTest() {

        Bowling bowling = new Bowling();
        CustomPrintStream outputStream = new CustomPrintStream(System.out);
        System.setOut(outputStream);
        bowling.main(new String[]{"playsWithStrikes.txt"});

        List<String> lines = outputStream.getLines();
        assertEquals("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10", lines.get(0));
        assertEquals("Jeff", lines.get(1));
        assertEquals("Pinfalls\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\t", lines.get(2));
        assertEquals("Score\t\t30\t\t60\t\t90\t\t120\t\t150\t\t180\t\t210\t\t240\t\t270\t\t300\t\t320\t\t",
                lines.get(3));
        assertEquals("John", lines.get(4));
        assertEquals("Pinfalls\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\t", lines.get(5));
        assertEquals("Score\t\t30\t\t60\t\t90\t\t120\t\t150\t\t180\t\t210\t\t240\t\t270\t\t300\t\t320\t\t",
                lines.get(6));

    }

    @Test
    public void testBowlingPlaysWithZeroTest() {

        Bowling bowling = new Bowling();
        CustomPrintStream outputStream = new CustomPrintStream(System.out);
        System.setOut(outputStream);
        bowling.main(new String[]{"playsWithZero.txt"});

        List<String> lines = outputStream.getLines();
        assertEquals("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10", lines.get(0));
        assertEquals("Jeff", lines.get(1));
        assertEquals("Pinfalls\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t", lines.get(2));
        assertEquals("Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t",
                lines.get(3));
        assertEquals("John", lines.get(4));
        assertEquals("Pinfalls\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t", lines.get(5));
        assertEquals("Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t",
                lines.get(6));

    }

}
