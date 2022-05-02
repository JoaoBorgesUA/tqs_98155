Feature: HW1 testing

	Scenario: Requesting data of Portugal
		When I navigate to "http://localhost:8080/"
		And I select the searchbar and type "Portugal"
		And I click on the Submit button
		Then the title of the page should be "Country"
		And there should be a card with the title "Country" and value "Portugal"

