# Raven CLI

### Version 0.1.0-SNAPSHOT

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

## Features

- Command-line interface for running and testing JSON-based test cases.
- Clear per-test-case result reporting.
- Extensible with subcommands.
- Easy integration with CI/CD pipelines.
- Open source and ready for collaboration.

## Requirements

- Java 17 or higher (SDK)
- Apache Maven 3.9.9 or higher
- Picocli 4.7.7

## Geting Started

### 1. Instalation

### Seting up test directory

- In your root project create the directory `raven/tests`.
- Store your `.json test cases`
- You can organize your `test cases` within custom subdirectories:
  ```
  root/
  |-- raven/
  |   └─ tests/
  |       |- myTestCase1.json
  |       └─ subfolder/
  |           └─ myTestcase2.json
  |
  |-- src/
  ```

### 2. Defining your test cases.

- Create a `.json` file within the `raven/tests` directory.
- Define the parameters of your request: url, http method, request body (optional). For example:
  ```
  {
      "api_uri": "https://api.example.com,
      "request_method": "POST"
      "request_body": {
          "key": "value"
      }
  }
  ```

- Within the same test case define what you expect from the request as response. For example:
  ```
  {
      ...
      "expected_response" : {
          "status": 200,
          "body": {},
      }
  }
  ```

- Your test case must look as following:
  ```
  {
      "api_uri": "https://api.example.com,
      "request_method": "POST"
      "request_body": {
          "key": "value"
      },
      "expected_response" : {
          "status": 200,
          "body": {},
      }
  }
  ```

### 3. Runing your test cases.

Nice! You've defined your first test case. Now it's time to run it and see what happens.

- Run the following command from the root project:
    ``` 
    raven run test_case_name_or_path
    ```

- For example:
    ```
    raven run myTestcase1.json
    ```

- Or:
    ```
    raven run /subfolder/myTestcase2.json
    ```