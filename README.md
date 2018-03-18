# Chilindo OpenWeatherMap Application

[![Build Status](https://travis-ci.org/gahfy/chilindoweather.svg?branch=master)](https://travis-ci.org/gahfy/chilindoweather) [![Codecov](https://codecov.io/github/gahfy/chilindoweather/coverage.svg?branch=master)](https://codecov.io/gh/gahfy/chilindoweather)

A demo weather app for interview with Chilindo

## Architecture

The project has been developed using an MVP architecture. This has been made because the project is not complex enough to take advantage of an MVVM or clean architecture.

It is the same about not using architecture components. As there is no storage strategy but the shared preferences, including Architecture Components in order to use only LiveData was not relevant.

### Continuous Integration

#### Travis

[![Build Status](https://travis-ci.org/gahfy/chilindoweather.svg?branch=master)](https://travis-ci.org/gahfy/chilindoweather)

Travis CI is used for continuous integration. It mainly checks that:

* The application builds in debug and release
* The unit tests are working in debug and release
* The instrumented tests are working in debug and release

After checking all of the above, Travis generates Jacoco report to get Code Coverage, and sends it to codecov.io.

It allows also to display result of build on a pull request, so it is more easy to detect if something fails before validating a pull request.

#### Codecov.io

[![Codecov](https://codecov.io/github/gahfy/chilindoweather/coverage.svg?branch=master)](https://codecov.io/gh/gahfy/chilindoweather)

Codecov receives the code coverage report from Travis, and allows to display it nicely for the project.

<p style="text-align:center;">
    <img src="https://codecov.io/gh/gahfy/chilindoweather/branch/master/graphs/sunburst.svg" alt><br /><em>image_caption</em>
</p>

It allows also to display difference of code coverage when a pull request is made, so it is more easy to detect if there is a lack of unit tests.

### Libraries

#### Application libraries

##### Retrofit 2

Retrofit is used to perform HTTP request. It is used instead of Volley for the following reasons:

* Extensibility: which allows use of many extension for deserialization is needed

##### Moshi

Moshi is used to deserialize Json into POJO instances.

##### RxJava / RxAndroid

RxJava allows to subscribe to end of asynchronous task. It is only used with Retrofit in this project. The main advantage of RxJava against Callable is the ability to use operators such as `map()` or `zip()` event if it is not used in this project.

##### Dagger 2

Dagger 2 allows to nicely manage dependency injection. It is used in order to deal with `Context` related classes in presenters.

##### Firebase

Firebase is included in order to provide Google Authentication which is required by the specification.

##### Glide

Glide is used to get image from internet (profile picture) and to deal with big image loading (background of the header of `NavigationDrawer`).

#### Unit test Libraries

##### Mockito

As we use `Context` related objects in presenters, we use Mockito in unit tests in order to mock instances of those objects.

##### Gson

Actually, Json deserialization is performed by Moshi, but Gson is used in order to use `TypeToken` in JSON deserialization.


### API Key and Keystore password

It was written in specifications that *A complete project (Android Studio) which **BUILDS** and runs without errors, generating an
application binary.*

This constraint forced me not to add the keystore folder in `.gitignore`. Without that constraint, the keystore directory would have been added to `.gitignore` and all security data would have been set in the **user** `gradle.properties` file instead of the app one.
