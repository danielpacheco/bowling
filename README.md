# Jobsity bowling

From the PDF I believe I've done all the tasks, including:


"Your program should be able to handle all possible cases of a game both including:
- "a game where all rolls are 0,"
- "all rolls are fouls (F) and"
- a perfect game, where all rolls are strikes

- Unit test: Tests should cover at least the non-trivial classes and methods
- Integration test: At least cover the three main cases:
- Sample input (2 players),
- perfect score, zero score -> **Tested integration with files**

- "Code should depend on interfaces",


- validator on reader
 * The program should handle bad input like
 - more than ten throws -> **but as far as I understand this is valid if last one is a spare or a strike**
 - no chance will produce a negative number of knocked down pins
 - or more than 10 pins or frames -> **in case of frames, moves after 11 are ignored**
 - invalid score value
 - or incorrect format
"

__

To build:
    **gradle build**

To run tests:
    **gradle tests**

After building, you can run the jar like this:
    **java -cp build/libs/bowling-0.0.1-SNAPSHOT.jar com.jobsity.bowling.Bowling plays1.txt**
