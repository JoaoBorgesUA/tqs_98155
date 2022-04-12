Feature: Login in practice site

  Scenario: Successful login
    When I navigate to "https://bonigarcia.dev/selenium-webdriver-java/login-form.html"
    And I login with the username "user" and password "user"
    And I click Submit
    Then I should be see the message "Login successful"

  Scenario: Failure login
    When I navigate to "https://bonigarcia.dev/selenium-webdriver-java/login-form.html"
    And I login with the username "bad-user" and password "bad-password"
    And I click Submit
    Then I should be see the message "Invalid credentials"

  Scenario: Flight from Paris to Rome
    When I navigate to "https://blazedemo.com/"
    And I click to arrive in Rome
    And I click Find Flights
    And I choose the first flight
    Then the Total Cost should be 914.76

