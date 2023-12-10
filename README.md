## About

`macros-cli` is a front-end command-line interface tool designed to interact with the [Macros backend service](https://github.com/physine/macros). It offers a convenient way to perform CRUD operations on macros via simple CLI commands, leveraging the functionalities provided by the backend service.


# Macros-CLI

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

Build the project
```bash
mvn clean install
```

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


## Environment Setup Instructions

In addition to setting up the Java environment, you can configure your system to run the `macro` command from anywhere in the command line by updating the `PATH` environment variable.

## Adding `macro` to the PATH

1. **Locate the `macro` Executable**:
    - Find the directory where your `macro` executable (or script) is located.
    - For example, it might be in `C:\path\to\macros-cli\target` on Windows or `/path/to/macros-cli/target` on Unix-like systems.

2. **Update the `PATH` Environment Variable**:

## Verifying the Setup

After setting up the environment variables, restart your command prompt or terminal and run:

```bash
java -version
```

## Contributing

Contributions are welcome! Feel free to submit pull requests, open issues, or suggest improvements.

