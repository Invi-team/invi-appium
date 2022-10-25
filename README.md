# invi-appium


### local env setup

##### Clone this repo (install mvn and java)
````
git clone https://github.com/flowerasny/invi-appium.git
````

##### Create config.properties

Create config properties file in /main/resources directory and
set values for keys:
````
appium.host
invi.api.baseurl
invi.accounts.password
````
##### Create local test suite

Create file named testng-local.xml in /main/resources catalog, eg.

````
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd" >

<suite name="allAndroidEmulator" verbose="1" >
    <listeners>
        <listener class-name="invi.listeners.SuiteListener"></listener>
    </listeners>
    <parameter name="device" value="invi.capabilities.EmulatorLocal"></parameter>
    <parameter name="system" value="Android"></parameter>
    <test name="android">
        <classes>
            <class name="invi.tests.auth.LogInTest" ></class>
            <class name="invi.tests.auth.SignUpTest"></class>
        </classes>
    </test>
</suite>
````

##### install appium
````
brew install appium
````

##### Create android emulators
Open android AVD Manager and create virtual devices
Platform version must be set to 11 (or different, but equal to 'platformVersion' capability in EmulatorLocal class)

##### Download and install Appium Desktop
https://github.com/appium/appium-desktop/releases/tag/v1.22.2

### local run

- move app-debug.apk to main project's directory

- start android emulator

- start appium server

- start test suite from Intellj Idea

### AWS Device Farm run

- move app-debug.apk to main project's directory
- create config.properties (see local env setup)
- create aws.properties in /main/resources
and init values for keys:
````
aws.project.arn
aws.testspec.android.arn
aws.testspec.ios.arn
aws.pool.arn
````
- set up AWS credentials on your machine https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/setup-credentials.html

- start command to build zip-with-dependencies.zip
````
mvn clean install -DskipTests=true 
````
 - run App.java\
set Android or iOS as argumant, Android is default

````
mvn exec:java -Dexec.args="Android"
````

