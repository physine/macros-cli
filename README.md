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

Run `macros-cli` with the desired command:

- **Create a Macro**: `java -jar macros-cli.jar -c <target> <trigger>`
- **Retrieve All Macros**: `java -jar macros-cli.jar -r`
- **Update a Macro**: `java -jar macros-cli.jar -u <id> <new_target> <new_trigger>`
- **Delete a Macro**: `java -jar macros-cli.jar -d <id>`

## Contributing

Contributions are welcome! Feel free to submit pull requests, open issues, or suggest improvements.

