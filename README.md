# nui-lotus-template

## Tech Stack
- Taleggio - Test case and Report management
- Cucumber - Framework, Story files, and Step definition mapping
- Testng - Story file execution
- Gradle 7.6 - Build, compiler, modularization
- Open JDK 13

## Setup Instructions
**This repo is a template only. Please copy this project and push any changes to a new repo.**
1. Click 'Use this template' to create your team's repository
2. Import project in IDE
4. Clean and build the project with Gradle
5. Start writing test cases in the feature files

## Template instructions
1. Rename each Java class based on team functionality
2. In each class, look for TODO comments for places where code should be updated

## Test Execution instructions
1. Configure TestRunner to run specific feature file(s)
2. Run TestRunner class using any of the below options \
   a. using localTest task **./gradlew localTest** \
   b. Click Run 'TestRunner'  and select localTest

## Support Information
- More information can be found in this working document: https://salesforce.quip.com/GIoiAY8YQa2b
- These instructions will be published to Confluence when ready.

```diff
- NOTE: All the below listed framework dependencies are not required to add in your pom.xml as dependency to compile the project.
+ force-partner-api
+ force-wsc
+ selenium-java
+ webdrivermanager
+ Tooling
+ cucumber-java
+ cucumber-testng
```

## Useful Gradle commands

* Run tests only:

  ```./gradlew localTest```

* Run Build without quality checks (pmd, checkstyle,jacocoTestReport,spotbugs):

  ``` ./gradlew build -x check```

* Run Build with all quality checks (pmd, checkstyle,jacocoTestReport,spotbugs):

  ``` ./gradlew build -x check```

* Run quality checks separately (pmd, checkstyle,jacocoTestReport,spotbugs):

  ``` ./gradlew checkstyleMain```
  ``` ./gradlew checkstyleTest```
  ``` ./gradlew pmdMain```
  ``` ./gradlew pmdTest```

* List all properties on the build

  ```./gradlew -q properties```

* List all tasks on the build

  ```./gradlew -q tasks```

* List build configuration model

  ```./gradlew -q model```

* Execute tasks parallel:

  ```./gradlew clean build --parallel```

* Skip test:

  ```./gradlew build -x test```

* Run application tests with debugger agent

  ```./gradlew localTest --debug-jvm```

* Refresh dependencies

  ```./gradlew clean build --refresh-dependencies```

* Gradle jar Cache Path:
/Users/NAME/.gradle/caches/modules-2/files-2.1


## LogLevel Guidance
To change the log level update the gradle.properties file to required levels. Find the loglevel options below.
  -q , –quiet	Set Gradle log level QUIET to print errors only.
  -w , –warn	Set Gradle log level WARN to print warnings and errors.
  -i , –info	Set Gradle log level INFO to print info, warning and errors.
  -d , –debug	Set Gradle log level DEBUG to print debug, info, warning and errors.
  No Log Option	Default log level LIFECYCLE once you not set any. It prints gradle life cycle all statements to execute a task..