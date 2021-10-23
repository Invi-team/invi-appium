# invi-appium


### local env setup

##### clone this repo (install mvn and java)
````
git clone https://github.com/flowerasny/invi-appium.git
````

##### install appium
````
brew install appium
````

##### create android emulators
Open android AVD Manager and create virtual devices
Name of device must be then set as browserName in nodeconfig file


##### download Selenium Grid server (version 3)
Go to https://www.selenium.dev/downloads/ and find selenium-server-standalone-3.141.59.jar - lastest version of selenium 3
(appium still does not work well with version 4)

### local run

- move app-debug.apk to main project's directory

- start android emulator (emulator's udid must be set in nodeconfig file)

- open first terminal window and run previosuly downloaded selenium server jar as hub
export ANDROID_SDK_ROOT first - appium has issue to detect the variable.

````
export ANDROID_SDK_ROOT=/Users/{user}/Library/Android/sdk
java -jar selenium-server-standalone-3.141.59.jar -role hub
````

- run appium node
````
export ANDROID_SDK_ROOT=/Users/{user}/Library/Android/sdk
appium -p 4723 --nodeconfig nodeconfig-android.json
````

- go to grid console (localhost:4444/grid/console) and verify if emulator is listed there

- start test suite from Intellj Idea
