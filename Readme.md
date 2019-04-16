Libraries and Plugin Used:

Cucumber-jvm (https://cucumber.io/docs/reference/jvm#java)
Cucumber gradle parallel (https://github.com/camiloribeiro/cucumber-gradle-parallel)


Pre-requisite:

Copy browser specific drivers to drivers

Provide below parameters to AutomaitonConfig.properties
video.capture=true/false
video.format=avi


(Optional) Install docker & docker-compose -- For headless run


Download and invoke Zalenium grid images https://github.com/zalando/zalenium#run-it -- Try it really cool !!!



Components:

Groovy scripts:

TaskExecutor: Driver script for cucumber (run parallel and sequential)
Utils: Implementations of miscellaneous functions
Reporter: Generate cucumber report


Step definitions: Contains implementations of cucumber steps, interacts with business flows
Business flows: Additional layers which can call other business flows or screen methods
Screens: Implementation of pages for mobile apps
Have descriptive logging using log4j
Utilities:

Custom Asserts: Extension of hemcrest assert, which provide crisp assert messages
DriverFactory: Gives driver objects based on gradle task
KEYS: An enum for relevant properties
ScreenShotUtils: Captures screenshots of browsers, Also captures video recording and attached to reports




Run:
run=@AddToCart/@e2e/@SelectExperience ./gradlew clean runCukes -Dmode=<local/remote> -Dbrowser.type=<firefox/chrome>
Note: In order to run test inside headless mode (Container), below command has to be invoke
docker-compose up -d or invoke zalenium (Using its live dashboard you can see parallel run)
run=@AddToCart/@e2e/@SelectExperience ./gradlew clean runCukes -Dmode=remote -Dbrowser.type=<firefox/chrome>

Reports:

After a run reports will be generated to /testRun/report_MMddyyhhmmss/cucumer/cucumber-html-reports/overview-features.html
Last screenshot of scenario also attached to report


Reports Images:



Scope for further extensions:

Parallel or Distributed execution at feature and scenario levels


References:


Organize logic using build src
https://docs.gradle.org/current/userguide/organizing_build_logic.html#sec:build_sources


Run cucumber parallel
https://github.com/camiloribeiro/cucumber-gradle-parallel


Cucumber and Infra
https://github.com/anandbagmar/cucumber-jvm-appium-infra
