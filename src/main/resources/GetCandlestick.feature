Feature: API Testing for Crypto.com

  Scenario: Health Check
    Given the service is available
    When the instrument is set to BTC_USDT and timeframe is 5m
