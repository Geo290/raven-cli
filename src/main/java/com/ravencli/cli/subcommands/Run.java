package com.ravencli.cli.subcommands;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.ravencli.controller.Controller;
import com.ravencli.file.model.TestCase;

@Command(name = "run", description = "Runs the given test cases")
public class Run implements Runnable {
    @Parameters(index = "0", description = "Test case relative path")
    private String relativeFilePath;

    @Override
    public void run() {
        File file = Controller.readFile(relativeFilePath);
        TestCase testCase = Controller.buildTestCase(file);
        HttpRequest request = Controller.buildHttpRequest(testCase);
        HttpResponse<String> response = Controller.executeRequest(request);
        Controller.runTestCase(testCase, response);
    }
}
