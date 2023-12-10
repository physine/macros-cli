package org.example.services;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.cli.CommandLine;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

public class MacroService {

    private final ApiService apiService;
    private final Gson gson;

    public MacroService(ApiService apiService) {
        this.apiService = apiService;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
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
            JsonElement jsonElement = JsonParser.parseString(response.body());
            return gson.toJson(jsonElement);
        }

        if (cmd.hasOption("retrieve")) {
            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:4567/api/v1/macros"))
                    .GET()
                    .build();

            HttpResponse<String> response = apiService.sendRequest(getRequest);
            JsonArray jsonArray = JsonParser.parseString(response.body()).getAsJsonArray();

            StringBuilder table = getStringBuilder(jsonArray);
            return table.toString();
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

    private static StringBuilder getStringBuilder(JsonArray jsonArray) {
        // Determine the maximum lengths of trigger and target
        int maxTriggerLength = "Trigger".length();
        int maxTargetLength = "Target".length();
        for (JsonElement element : jsonArray) {
            JsonObject macro = element.getAsJsonObject();
            maxTriggerLength = Math.max(maxTriggerLength, macro.get("trigger").getAsString().length());
            maxTargetLength = Math.max(maxTargetLength, macro.get("target").getAsString().length());
        }

        StringBuilder table = new StringBuilder();

        // Markdown Table Header
        String headerFormat = "| %-3s | %-" + maxTriggerLength + "s | %-" + maxTargetLength + "s |\n";
        table.append(String.format(headerFormat, "ID", "Trigger", "Target"));

        // Markdown Table Separator
        table.append("|" + "-".repeat(5) + "|" + "-".repeat(maxTriggerLength + 2) + "|" + "-".repeat(maxTargetLength + 2) + "|\n");

        // Table Rows
        String rowFormat = "| %-3d | %-" + maxTriggerLength + "s | %-" + maxTargetLength + "s |\n";
        for (JsonElement element : jsonArray) {
            JsonObject macro = element.getAsJsonObject();
            Long id = macro.get("id").getAsLong();
            String trigger = macro.get("trigger").getAsString();
            String target = macro.get("target").getAsString();

            // Formatting each row as a Markdown table row
            table.append(String.format(rowFormat, id, trigger, target));
        }
        return table;
    }
}