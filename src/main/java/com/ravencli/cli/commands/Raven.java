package com.ravencli.cli.commands;

import picocli.CommandLine.Command;
import com.ravencli.cli.subcommands.Run;

@Command(
    name = "raven",
    mixinStandardHelpOptions = true,
    version = "v0.1.0-SNAPSHOT",
    subcommands = {
        Run.class
    }
)
public class Raven implements Runnable{

    @Override
    public void run() {

    }
}
