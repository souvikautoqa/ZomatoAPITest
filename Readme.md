<b>Libraries and Plugin Used:</b>

Cucumber-jvm (https://cucumber.io/docs/reference/jvm#java)

Maven (https://maven.apache.org/)

Restassured (https://github.com/rest-assured/rest-assured/wiki/GettingStarted)


<b>Pre-requisite:</b>

> Clone the project 

> Update ZOMATO_APIKEY_VALUE in src/main/resources/ZomatoConfiguration.properties to authenticate 


<b>Components:</b>

a)  Cucumber scripts - features/ZomatoAPITestSuite.feature
      
      Scenario Outline: As an user I want to search for restaurants in my city
			
      Scenario Outline: As an user I want to search for specific cuisine restaurants in my city
			
      Scenario Outline: As an user I want to search for restaurants in my city by specific category
      
      Scenario Outline: As an user I want to view reviews of my favourite restaurants
 		
      Scenario Outline: As an user I want to view the details of my restaurant

b)  Test Runner - runner/ZomatoAPITestRunner

c)  API Test Framework - common/api

d)  Utils - common/util

e)  POJO classes - /model

f)  Zomato API methods - /zomatoapis

g)  Step definitions: Contains implementations of cucumber steps, interacts with business flows

<b>Run:</b> 
	
	Clone the project and run command - mvn test

<b>Reports:</b> 
        
	Default cucumber report will be generated in target/cucumber-reports

