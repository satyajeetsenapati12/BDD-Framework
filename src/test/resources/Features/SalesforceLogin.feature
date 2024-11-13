Feature: User Logged in Successfully

  Scenario Outline: User Logged in with Valid Credentials
    Given Open Browser
    Then enter the URL
    Then enter <username> and <password>
    Then validate user logged in successfully

    Examples: 
      | username                   |  | password       |
      | Satyajeet12@jadeglobal.com |  | Satyajeet@1234 |

      