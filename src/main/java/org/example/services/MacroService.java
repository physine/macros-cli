package org.example.services;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MacroService {
    private final ApiService apiService;

    public MacroService(ApiService apiService) {
        this.apiService = apiService;
    }

    public String executeCommand(CommandLine cmd) throws IOException, InterruptedException {
        if (cmd.hasOption("create")) {
            String[] createArgs = cmd.getOptionValues("create");
            String target = createArgs[0];
            String trigger = createArgs[1];
            String jsonPayload = String.format("{\"trigger\": \"%s\", \"target\": \"%s\"}", trigger, target);

            HttpRequest postRequest = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:4567/api/v1/new-macro"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                    .build();

            HttpResponse<String> response = apiService.sendRequest(postRequest);
            return response.body();
        }

        if (cmd.hasOption("retrieve")) {
            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:4567/api/v1/macros"))
                    .GET()
                    .build();

            HttpResponse<String> response = apiService.sendRequest(getRequest);
            return response.body();
        }

        if (cmd.hasOption("update")) {
            String[] updateArgs = cmd.getOptionValues("update");
            Long id = Long.parseLong(updateArgs[0]);
            String target = updateArgs[1];
            String trigger = updateArgs[2];
            String jsonPayload = String.format("{\"id\": %d, \"trigger\": \"%s\", \"target\": \"%s\"}", id, trigger, target);

            HttpRequest postRequest = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:4567/api/v1/update-macro"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                    .build();

            HttpResponse<String> response = apiService.sendRequest(postRequest);
            return response.body();
        }

        if (cmd.hasOption("delete")) {
            Long id = Long.parseLong(cmd.getOptionValue("delete"));
            String idString = id.toString();

            HttpRequest deleteRequest = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:4567/api/v1/delete-macro"))
                    .header("Content-Type", "text/plain")
                    .method("DELETE", HttpRequest.BodyPublishers.ofString(idString))
                    .build();

            HttpResponse<String> response = apiService.sendRequest(deleteRequest);
            return response.body();
        }

        if (cmd.hasOption("help")) {
            return "Run without args for help options.";
        }

        return null;
    }
}