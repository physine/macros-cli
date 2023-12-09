package org.example;

import org.apache.commons.cli.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class App {
    public static void main( String[] args ) {

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

        // Rest of the CLI setup and logic

        HttpClient httpClient = HttpClient.newHttpClient();
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("create")) {
                String[] createArgs = cmd.getOptionValues("create");
                String trigger = createArgs[0];
                String target = createArgs[1];

                // Construct JSON payload
                String jsonPayload = String.format("{\"trigger\": \"%s\", \"target\": \"%s\"}", trigger, target);

                // Create a POST request
                HttpRequest postRequest = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:4567/api/v1/new-macro"))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                        .build();

                // Send the request and get the response
                HttpResponse<String> response = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());

                System.out.println(response.body());
            }

            if (cmd.hasOption("retrieve")) {
                HttpRequest getRequest = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:4567/api/v1/macros"))
                        .GET()
                        .build();
                HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

                System.out.println(getResponse.body());
            }

            if (cmd.hasOption("update")) {
                String[] updateArgs = cmd.getOptionValues("update");
                Long id = Long.parseLong(updateArgs[0]);
                String target = updateArgs[1];
                String trigger = updateArgs[2];

                // Construct JSON payload
                String jsonPayload = String.format("{\"id\": %d, \"trigger\": \"%s\", \"target\": \"%s\"}", id, trigger, target);

                // Create a POST request
                HttpRequest postRequest = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:4567/api/v1/update-macro"))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                        .build();

                // Send the request and get the response
                HttpResponse<String> response = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());

                System.out.println(response.body());
            }

            if (cmd.hasOption("delete")) {
                Long id = Long.parseLong(cmd.getOptionValue("delete"));

                // Convert the id to a string and put it in the request body
                String idString = id.toString();

                // Create a DELETE request
                HttpRequest deleteRequest = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:4567/api/v1/delete-macro"))
                        .header("Content-Type", "text/plain") // Set content type as text/plain
                        .method("DELETE", HttpRequest.BodyPublishers.ofString(idString))
                        .build();

                // Send the request and get the response
                HttpResponse<String> response = httpClient.send(deleteRequest, HttpResponse.BodyHandlers.ofString());

                System.out.println(response.body());
            }

        } catch (ParseException | IOException | InterruptedException e) {
            System.err.println("Parsing failed. Reason: " + e.getMessage());
            new HelpFormatter().printHelp("macro-cli", options);
        }

    }
}
