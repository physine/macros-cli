package org.example.cliOptions;

import org.apache.commons.cli.*;

public class CliOptions {
    public Options defineOptions() {
        Options options = new Options();

        // Create
        Option create = Option.builder("c")
                .longOpt("create")
                .numberOfArgs(2)
                .argName("<target> <trigger>")
                .desc("Create a new macro. Requires target and trigger as arguments.")
                .build();
        options.addOption(create);

        // Retrieve
        Option retrieve = new Option("r", "retrieve", false,
                "Retrieve all macros.");
        options.addOption(retrieve);

        // Update
        Option update = Option.builder("u")
                .longOpt("update")
                .numberOfArgs(3)
                .argName("<id> <target> <trigger>")
                .desc("Update an existing macro. Requires macro ID, target, and trigger.")
                .build();
        options.addOption(update);

        // Delete
        Option delete = Option.builder("d")
                .longOpt("delete")
                .hasArg()
                .argName("<id>")
                .desc("Delete a macro. Requires the macro ID.")
                .build();
        options.addOption(delete);

        // Help
        Option help = Option.builder("h")
                .longOpt("help")
                .hasArg(false)
                .desc("Display help information about commands.")
                .build();
        options.addOption(help);

        return options;
    }

    public CommandLine parseOptions(Options options, String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        return parser.parse(options, args);
    }
}