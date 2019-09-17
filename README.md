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

Also refactored:

"
* Integer  comparative using == instead of equals --> boxing handles it, but change it to match your style
* Control statement without braces --> this is more of code style, but fix it to match yours
* Useless parentheses --> couldn't find them, please tell me class and line
* for (; could be replaced with while
* Should use Logger instead of System.out.println or e.printStackTrace() for logging purpose.
* Unnecessary modifier on interfaces
* Idea folder and project file
* Should use custom exceptions class.
* No main manifest attribute in jar
* Allows more than 10 in two throws: Jeff 8 Jeff 8
* No reals unit test --> there was: Reader Test, but I added more
* Just a few integration tests. --> I added more integration tests
* Should use the interface as type, not the specific implementation         
	MapScoreObtainer scoreObtainer = new MapScoreObtainer();
"

__

To build:
    **gradle build**

To run tests:
    **clean test --info**

After building, you can run the jar like this:
    **java -cp build/libs/bowling-0.0.1-SNAPSHOT.jar com.jobsity.bowling.Bowling plays1.txt**
    or **java -jar build/libs/bowling-0.0.1-SNAPSHOT.jar playsInPDF.txt**
