# The Project is consisted some automation tests of site https://www.officemag.ru/
___
It created UI tests by test cases. [See here](src/docs/testdocumentation.xlsx):
1. the main page - 9 tests in `class MainPageTests`.
2. the Registration form - 2 tests and one "disabled" test in `class RegistrationFormPageTests`.
3. the Sign-In form - 4 tests in `class SignInFormTests`.

## USAGE 
You can start tests local and remote.
The properties of the project would be set in:
* system properties
* resources/config/local.properties
* resources/config/remote.properties

*Note: It uses the load strategy "MERGE" (See 1 in Miscellaneous)*

The list of properties are
* browser (default chrome)
* browserVersion (default 89.0)
* browserSize (default 1920x1080)
* browserMobileView (mobile device name, for example iPhone X)
* remoteDriverUrl (url address from selenoid or grid)
* videoStorage (url address where you should get video)
* threads (number of threads)

The data of existed user for tests are in file `src/test/resources/config/users/existuser.properties`.

### For run local tests need fill local.properties or to receive system properties by command line (see 2 Miscellaneous).
1. The quick start
   1. Open a terminal and write
    ```
    ./gradlew test
    ``` 
   Or       
    ```
      ./gradlew <a name of a class>.<a name of a method> test
    ```
*Note: Before you should move to the work directory of the project*.   
2. The start Allure report
```
./build/allure/commandline/bin/allure serve build/allure-results
```
### For run remote tests need fill remote.properties (or local.properties) or to pass value:
Run tests with filled remote.properties:
```bash
gradle clean test
```
Run tests with not filled remote.properties:
```bash
gradle clean -DremoteDriverUrl=https://%s:%s@selenoid.autotests.cloud/wd/hub/ -DvideoStorage=https://selenoid.autotests.cloud/video/ -Dthreads=1 test
```
Serve report:
```bash
allure serve build/allure-results
```

## Miscellaneous
1. The load strategy "MERGE" (docs OWNER):
>All the specified sources will be loaded and merged. If the same property key is specified from more than one source, the one specified first will prevail.
2. Use command line
```
   ./gradlew -D[key]=[value] -D[key1]=[value1] -D[key1]=[value1] test
``` 