# invi-appium


### local env setup

##### clone this repo (install mvn and java)
````
git clone https://github.com/flowerasny/invi-appium.git
````

##### create config.properties

create config properties file in resources directory
set values for keys:
- appium.host
- invi.api.baseurl
- invi.accounts.password

##### create local test suite

create file named testng-local.xml in /test/resources catalog, eg.

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

##### create android emulators
Open android AVD Manager and create virtual devices
Platform version must be set to 11 (or different, but equal to 'platformVersion' capability in EmulatorLocal class)

##### download and install Appium Desktop
https://github.com/appium/appium-desktop/releases/tag/v1.22.2

### local run

- move app-debug.apk to main project's directory

- start android emulator

- start appium server

- start test suite from Intellj Idea


