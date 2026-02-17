📱 Todo App - Android Automation Testing
Mobile test automation framework for Todo Android application using Appium, Cucumber BDD, and Java.

🛠️ Tech Stack
TechnologyVersionPurposeJava21Programming languageAppium2.xMobile automationCucumber7.xBDD frameworkJUnit4.xTest runnerMaven3.xBuild toolAndroid SDK-Android support

📁 Project Structure
src/
├── test/
│   ├── java/
│   │   └── com/qa/
│   │       ├── pages/
│   │       │   ├── BasePage.java         # Base page with common methods
│   │       │   └── CreateTaskPage.java   # Task page interactions
│   │       ├── stepdef/
│   │       │   ├── Hooks.java            # Before/After hooks
│   │       │   └── CreateTaskStepDef.java # Step definitions
│   │       ├── runners/
│   │       │   └── MyRunnerTest.java     # Cucumber test runner
│   │       └── utils/
│   │           ├── DriverManager.java    # Appium driver management
│   │           ├── ServerManager.java    # Appium server management
│   │           ├── CapabilityManager.java # Device capabilities
│   │           ├── GlobalParams.java     # Global parameters
│   │           ├── PropertyManager.java  # Property file manager
│   │           └── TestUtils.java        # Utility methods
│   └── resources/
│       ├── CreateTask.feature            # Task creation scenarios
│       └── CompleteTask.feature          # Task completion scenarios
└── pom.xml

⚙️ Prerequisites
Make sure you have the following installed:

✅ Java 21+
✅ Maven 3.x
✅ Node.js
✅ Appium 2.x
✅ Android SDK
✅ Android Emulator or Physical Device


🚀 Setup
1. Clone the Repository
bashgit clone https://github.com/MariAlipashayeva/todo-android-automation-project.git
cd todo-android-automation-project
2. Install Appium
bashnpm install -g appium
appium driver install uiautomator2
3. Set Environment Variables
bashexport ANDROID_HOME=/Users/your-username/Library/Android/sdk
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk
export PATH=$PATH:$ANDROID_HOME/platform-tools
4. Install Dependencies
bashmvn clean install -DskipTests

▶️ Run Tests
Run All Tests
bashmvn clean test
Run Specific Feature
bashmvn clean test -Dcucumber.features="src/test/resources/CreateTask.feature"
Run Specific Tags
bashmvn clean test -Dcucumber.filter.tags="@smoke"

📋 Test Scenarios
✅ Task Creation
ScenarioDescriptionCreate Multiple TasksCreates multiple tasks using data tableCreate Single TaskCreates individual task with title and description
✅ Task Completion
ScenarioDescriptionComplete First N TasksCompletes first N tasks dynamicallyComplete N Random TasksCompletes N randomly selected tasksComplete All TasksCompletes all tasks in the listUncomplete All TasksMarks all tasks as incompleteToggle All CheckboxesToggles all task checkboxesClear Completed TasksRemoves completed tasks from listVerify Task CountVerifies correct number of tasks

🏗️ Framework Architecture
┌─────────────────────────────────────────┐
│           Feature Files (BDD)            │
│        CreateTask.feature               │
│        CompleteTask.feature             │
└──────────────────┬──────────────────────┘
                   │
┌──────────────────▼──────────────────────┐
│           Step Definitions               │
│        CreateTaskStepDef.java           │
└──────────────────┬──────────────────────┘
                   │
┌──────────────────▼──────────────────────┐
│            Page Objects                  │
│   BasePage.java  │  CreateTaskPage.java  │
└──────────────────┬──────────────────────┘
                   │
┌──────────────────▼──────────────────────┐
│              Utilities                   │
│  DriverManager  │  ServerManager        │
│  CapabilityManager  │  TestUtils        │
└─────────────────────────────────────────┘

📱 Device Configuration
Update capabilities in CapabilityManager.java:
javacapabilities.setCapability("platformName", "Android");
capabilities.setCapability("deviceName", "your-device-name");
capabilities.setCapability("appPackage", "your.app.package");
capabilities.setCapability("appActivity", "your.app.activity");

📊 Test Reports
After running tests, reports are generated in:
target/cucumber.html    # Cucumber HTML report
target/surefire-reports # JUnit reports

🔧 Key Features

✅ Page Object Model - Maintainable and reusable code
✅ BDD with Cucumber - Human readable test scenarios
✅ Dynamic Task Creation - Create multiple tasks via data table
✅ Dynamic Completion - Complete first N, random N, or all tasks
✅ Auto Scroll - Automatically scrolls to off-screen elements
✅ ThreadLocal Driver - Supports parallel execution
✅ Screenshot on Failure - Captures screenshots on failed tests
✅ Appium Server Management - Automatic server start/stop


👩‍💻 Author
Mari Alipashayeva

GitHub: @MariAlipashayeva


📄 License
This project is for educational and portfolio purposes.
