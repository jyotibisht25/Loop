# Loop - Java Selenium Project

## Overview
This project is a Java Selenium automation framework for testing web applications. It utilizes Selenium WebDriver to interact with web elements and perform various test scenarios.

## Features
- **Page Object Model (POM):** Organizes web pages into separate classes to improve code readability and maintainability.
- **TestNG Integration:** Utilizes TestNG for test case management, execution, and reporting.
- **Data-Driven Testing:** Supports data-driven testing using external data sources like Excel files.
- **Logging:** Implements logging functionality for detailed test execution logs.
- **Configuration Management:** Centralizes configuration settings to manage test environment configurations easily.

## Prerequisites
Before running the tests, ensure you have the following installed:
- Java Development Kit (JDK)
- Maven
- WebDriver compatible with the browser you intend to automate (e.g., ChromeDriver for Chrome)

## Setup
1. Clone this repository to your local machine.
2. Ensure you have the prerequisites installed.
3. Update the `config.properties` file in the `src/main/resources` directory with appropriate configurations.
4. Download the WebDriver executable for your preferred browser and place it in a location accessible to the project.
5. Build the project using Maven:

    ```bash
    mvn clean install
    ```

## Running Tests
To execute the tests, run the following testng.xml file from eclipse:
![image](https://github.com/jyotibisht25/Loop/assets/104520755/6df738b3-e221-4c40-bb63-9e4e9772740f)

## Running Test CSV Output File
https://github.com/jyotibisht25/Loop/blob/main/chargebacks_payouts_overview.csv

## Recording for test cases
https://github.com/jyotibisht25/Loop/blob/main/Selenium%20Recording.mov
