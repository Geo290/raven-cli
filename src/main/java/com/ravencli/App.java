package com.ravencli;

import com.ravencli.cli.commands.Raven;

import picocli.CommandLine;

public class App {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new Raven())
            .setColorScheme(CommandLine.Help.defaultColorScheme(CommandLine.Help.Ansi.AUTO))
            .execute(args);
        System.exit(exitCode);
    }
}
