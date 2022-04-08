API Automated Testing

Get Candlestick endpoint

Tech Used:

1. RESTAssured Library for interacting with the API
2. Cucumber for BDD
3. Java Language
4. Junit & AssertJ Assertion Libraries
5. Maven Dependency Management

Design

I used Cucumber BDD as it provides a quick and efficient development framework for creating feature tests. This can be used to develop locally and the test runner provides ability for CI/CD running.

I used a TestContext class with a dependency injection tool, called pico-container which allows state and dependency to pass between Cucumber steps. This allows each step to read better in human readable language and also provides greater re-use, by having smaller modular steps.

Test Scenarios are based on using the API contract for the GET candlestick Endpoint. Using as much of the variables and automation to achieve relevant test coverage.

How to run a test:

1. Test can be run from GetCandlestick.feature file
