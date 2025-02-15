
@tag
Feature: Automating landing page search for Brit Insurance website

  @Funtional
  Scenario Outline: Brit Insurance Landing page
		Given I Landed on the Brit Insurance Landing page
    When  I Search for "IFRS 17"
    Then 	Following details should be listed as the search result
      | SearchResult1  	| SearchResult2 																					|	SearchResult3																	| SearchResult4												|	SearchResult5	|
      | Financials			|  Interim results for the six months ended 30 June 2022  |	Results for the year ended 31 December 2023		|	Interim Report 2023									| Kirstin Simon	|