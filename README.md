## About

`macros-cli` is a front-end command-line interface tool designed to interact with the [Macros backend service](https://github.com/physine/macros). It offers a convenient way to perform CRUD operations on macros via simple CLI commands, leveraging the functionalities provided by the backend service.

## Macros-CLI

Macros-CLI is a command-line interface tool for managing macros through a RESTful API. It simplifies the process of creating, retrieving, updating, and deleting macros with easy-to-use command-line operations. Built in Java, it leverages the Apache Commons CLI for parsing command-line arguments and Java HttpClient for HTTP requests, ensuring a smooth and efficient user experience.

## Features

- **Create Macros**: Easily add new macros with custom triggers and targets.
- **Retrieve Macros**: Fetch a list of all existing macros.
- **Update Macros**: Modify details of existing macros.
- **Delete Macros**: Remove macros that are no longer needed.

## Installation

Clone the repository:

```bash
git clone https://github.com/yourusername/macros-cli.git
cd macros-cli
```

Package the project
```bash
mvn package
```

## Macro Command Output Formatting

The `macro` command provides a neatly formatted table output for better readability. This is particularly useful when retrieving a list of macros. Here's what you can expect:

### Table Formatting

- **Column Headers**: The output includes headers for each column: 'ID', 'Trigger', and 'Target'.
- **Row Alignment**: Each row in the table aligns with the headers, presenting the data in an organized manner.
- **ID Indentation**: The 'ID' column values are indented by one space for visual clarity.
- **Separator Lines**: A line of dashes separates the headers from the rows, with plus signs aligning with the column dividers.
- **Readable Layout**: The table layout ensures that the data is easy to read and understand, with each macro represented as a row in the table.

## Usage

Run `macro` with the following options:

- **Create a New Macro**:
  - Command: `-c, --create <target> <trigger>`
  - Description: Create a new macro. Requires target and trigger as arguments.

- **Delete a Macro**:
  - Command: `-d, --delete <id>`
  - Description: Delete a macro. Requires the macro ID.

- **Display Help**:
  - Command: `-h, --help`
  - Description: Display help information about commands.

- **Retrieve All Macros**:
  - Command: `-r, --retrieve`
  - Description: Retrieve all macros.

- **Update an Existing Macro**:
  - Command: `-u, --update <id> <target> <trigger>`
  - Description: Update an existing macro. Requires macro ID, target, and trigger.

### Example Output

When you run the `macro -r` command, the output will look like this: <br>
Note: You do not need to start macro triggers with `!`, I just find that it works  well to avoid accidentally triggering macros.

```markdown
| ID  | Trigger   | Target                           |
|-----|-----------|----------------------------------|
| 1   | !example  | http://example.com               |
| 2   | !shortcut | /path/to/directory               |
| 3   | !alias    | alias@example.com                |
| 4   | !cmd      | Custom Command                   |
| 5   | !link     | https://www.example-link.com/    |
```

## Environment Setup Instructions

In addition to setting up the Java environment, you can configure your system to run the `macro` command from anywhere in the command line by updating the `PATH` environment variable.

### Adding `macro` to the PATH

1. **Locate the `macro` Executable**:
    - Find the directory where your `macro` executable (or script) is located, it should look like:
       ```batch
      @echo off
      java -jar macros-cli-1.0.jar %*
      ```
    - and it might be in `C:\path\to\macros-cli` on Windows or `/path/to/macros-cli` on Unix-like systems.

2. **Update the `PATH` Environment Variable**

## Contributing

Contributions are welcome! Feel free to submit pull requests, open issues, or suggest improvements.