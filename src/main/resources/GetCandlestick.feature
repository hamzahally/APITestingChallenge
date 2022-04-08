Feature: Checking CandleStick GET Endpoint for Crypto.com

  Scenario Outline: Check CandleStick Data for Multiple Instrument Types
    Given the service is available
    And the instrument is set to <instrument>
    And timeframe is 1m
    When the request is sent
    Then the response should return a 200 response
    Then response with instrument name of <instrument>
    And response with time frame of 1m

    Examples:
      | instrument |
      | BTC_USDT   |
      | CRO_USDT   |
      | ETH_USDT   |
      | AAVE_USDT  |
      | ALI_USDT   |
      | ALGO_USDT  |
      | ZIL_USDT   |
      | AXS_USDT   |
      | BAL_USDT   |

  Scenario Outline: Check CandleStick Data for Multiple Time Frames for BTC_USDT
    Given the service is available
    And the instrument is set to BTC_USDT
    And timeframe is <timeframe>
    When the request is sent
    Then the response should return a 200 response
    Then response with instrument name of BTC_USDT
    And response with time frame of <timeframe>

    Examples:
      | timeframe |
      | 1m        |
      | 5m        |
      | 15m       |
      | 30m       |
      | 1h        |
      | 4h        |
      | 6h        |
      | 12h       |
      | 1D        |
      | 7D        |
      | 14D       |
      | 1M        |

  Scenario: Data returned should be null when using a made-up instrument
    Given the service is available
    And the instrument is set to XXX_XXX
    And timeframe is 1m
    When the request is sent
    Then the response should return a 200 response
    Then the data should be null

  Scenario: Invalid Input Response when instrument name is missing
    Given the service is available
    And the instrument is set to empty
    And timeframe is 1m
    When the request is sent
    Then the response should return a 200 response
    Then response message is Invalid input

  Scenario: Bad Response when timeframe is missing
    Given the service is available
    And the instrument is set to BTC_USDT
    When the request is sent
    Then the response should return a 200 response
    Then response message is Timeframe null is not supported.

  Scenario: Correct data tags are in the response
    Given the service is available
    And the instrument is set to BTC_USDT
    And timeframe is 1m
    When the request is sent
    Then the response should return a 200 response
    And the response should contain the correct data tags