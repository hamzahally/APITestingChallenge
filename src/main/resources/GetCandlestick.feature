Feature: API Testing for Crypto.com

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

#  Scenario: Bad Response when instrument name is missing
#    Given the service is available
#    And the instrument is set to empty
#    And timeframe is 1m
#    When the request is sent
#    Then the response should return a 200 response
#    Then message is Invalid Input
#
#  Scenario: Bad Request when no instrument or time frame is supplied
#    Given the service is available
#    And no instrument of timeframe is set
#    When the request is sent
#    Then the response should return a 400 response
#    Then response has error message Bad Request


    #TODO: implement more scenario for candlestick endpoint
    #TODO: implement more scenario for checking headers
