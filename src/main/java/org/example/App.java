package org.example;

import org.apache.commons.cli.*;
import org.example.cliOptions.CliOptions;
import org.example.services.ApiService;
import org.example.services.MacroService;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        CliOptions cliOptions = new CliOptions();
        Options options = cliOptions.defineOptions();

        try {
            CommandLine cmd = cliOptions.parseOptions(options, args);
            ApiService apiService = new ApiService();
            MacroService macroService = new MacroService(apiService);

            String response = macroService.executeCommand(cmd);
            if (response != null){
                System.out.println(response);
            }else {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("macros-cli", options);
            }

        } catch (ParseException | IOException | InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
            new HelpFormatter().printHelp("macro-cli", options);
        }
    }
}
