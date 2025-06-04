package com.ravencli.cli.subcommands;

import java.io.File;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "run", description = "Runs the given test cases")
public class Run implements Runnable {
    @Parameters(index = "0", description = "Test case relative path")
    private String relativeFilePath;

    @Override
    public void run() {
        File testCaseFile = new File("raven/tests", relativeFilePath);
        System.out.println(testCaseFile.getAbsolutePath());
        if (!testCaseFile.exists()) {
            System.out.println("ERROR: TEST CASE NOT FOUND");
            System.out.println("Please ensure the test cases exist within the raven/tests directory");
            return;
        }
        System.out.println("Running test case" + testCaseFile.getAbsolutePath());
    }
}
