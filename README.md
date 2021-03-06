# Chilindo OpenWeatherMap Application

[![Build Status](https://travis-ci.org/gahfy/chilindoweather.svg?branch=master)](https://travis-ci.org/gahfy/chilindoweather) [![Codecov](https://codecov.io/github/gahfy/chilindoweather/coverage.svg?branch=master)](https://codecov.io/gh/gahfy/chilindoweather)
[![SonarCloud](https://sonarcloud.io/api/badges/gate?key=chilindoweather%3Aapp)](https://sonarcloud.io/dashboard?id=chilindoweather%3Aapp)
[![SonarCloud](https://sonarcloud.io/api/badges/measure?key=chilindoweather%3Aapp&metric=code_smells)](https://sonarcloud.io/dashboard?id=chilindoweather%3Aapp)
[![SonarCloud](https://sonarcloud.io/api/badges/measure?key=chilindoweather%3Aapp&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=chilindoweather%3Aapp)
[![SonarCloud](https://sonarcloud.io/api/badges/measure?key=chilindoweather%3Aapp&metric=sqale_index)](https://sonarcloud.io/dashboard?id=chilindoweather%3Aapp)
[![SonarCloud](https://sonarcloud.io/api/badges/measure?key=chilindoweather%3Aapp&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=chilindoweather%3Aapp)
[![SonarCloud](https://sonarcloud.io/api/badges/measure?key=chilindoweather%3Aapp&metric=alert_status)](https://sonarcloud.io/dashboard?id=chilindoweather%3Aapp)

A demo weather app for interview with Chilindo

[![Header](https://github.com/gahfy/chilindoweather/raw/master/img/header.png)](https://github.com/gahfy/chilindoweather)

## Before starting

### Worst technical test ever :-)

You asked me the worst technical test ever. Asking a weather application to a french guy who wants to live in Bangkok also for the climate is a torture. Just take a look at the picture!!! 0°C in Paris. And this screenshot has been taken from real live data!!! And I was always thinking about you, the reader, who is complaining about the coldness of your 25°C nights.

### What I'm proud about

I'm proud of providing a localized project, with nice design, settings and a complete CI. I'm also proud of having almost everything done in technical debt for this project.

I'm basically proud of having enough time to do everything requested in the specification, and a little bit more.

### What I'm not proud about

As said above, **ALMOST** everything done in technical debt. I did not have enough time to make at least 80% of code coverage with unit test. I mainly had only time to test models.

I also wish I had more time to make landscape and tablets designs. I only managed designs in all size and density portrait phones, but nothing else... Even if Landscape design is not that bad, it could have been optimized.

I'm also not proud of having localized the app only into three languages (english, arabic and french). I asked some friends of mine which were living in Thailand and in Spain to provide spanish and thai translation, but it was not done when finalizing the project.

Basically, if you would have given me two days, I would have been able to provide a minimal application (with less design, less CI, ...) and if you would have given me two years, I would still find something missing :-)

## Features

### Assumptions

#### Location

Finding the location may be something painful for the user. So as long as the user does not kill the application, there is no update of location before 60 minutes.
This assumption has been made as when discussing it with friends, a long distance trip takes usually at least one hour, and there is no need to check the weather of the current location while traveling.

#### Weather update

I discussed with many friends, and each one has a different point of view on refresh strategy.

*Some of the points of view*

* When I am in the subway or in poor connectivity strategy, it would be nice to have last weather instead of an error
* I would find it weird to have previous location for a small time, then the updated one when opening the application
* I would find it weird to have a loading time and then have old weather data if there is no network

So the best would have been to have a service that would try to update the weather each 30 minutes. But I gave the application to many friends in order to let them test it and report bugs, and there is a limit of number of calls with OpenWeatherMap free plan.

Because of that, I had to decide by myself, so I decided to make what is the most easy for a developer, so the weather is refreshed each time an Activity is created if there is no `savedInstanceState` with weather.

#### Dates and Times

The application only allows the user get the weather forecast for their current position (it is not possible to search for the weather forecast for an other place). This means that the current timezone of their phone matches the timezone of the weather forecast they actually get. This is why their is no timezone management in the application.

### Localization

The application is available in English and Arabic. Arabic is provided only to show compatibility with RTL languages.

<p align="center"><img src="https://github.com/gahfy/chilindoweather/raw/master/img/Screenshot_french_Chilindo%20Weather.jpg" width="250" /> <img src="https://github.com/gahfy/chilindoweather/raw/master/img/Screenshot_arabic_Chilindo%20Weather.jpg" width="250" /></p>

## Architecture

The project has been developed with Java as it was required by the specification.

The project has been developed using an MVP architecture. This has been made because the project is not complex enough to take advantage of an MVVM or clean architecture.

It is the same about not using architecture components. As there is no storage strategy but the shared preferences, including Architecture Components in order to use only LiveData was not relevant.

More information about how the architecture has been made can be found on a Medium article I recently published: [MVP Architecture with Kotlin — Dagger 2, Retrofit, RxAndroid and DataBinding](https://proandroiddev.com/mvp-architecture-with-kotlin-dagger-2-retrofit-rxandroid-and-databinding-17bffe27393d)

This article made me confident about the fact that this architecture is a good one. Because a lot of people commented the post in order to improve it, and as there is no more comments about improvements on this architecture.

This architecture is relevant as components are separated so anybody is able to work on specific task and a task is not depending on other one.

### Model Architecture

There are two packages for models:

* **api**: which are pojos. They are exactly the same as JSON response from OpenWeatherMap API. As there is no documentation about it on OpenWeatherMap, each field is considered as nullable.

* **weather**: those are models instantiated with API models, and which provide display methods, to be used in layout with DataBinding.

### UI Architecture

The UI Architecture of the application (Activities, Views and Presenters) consists in three layers:

* **Base** This layer contains useful methods that may be used by any Activity of any application. This layer allows us to add UI with a completely different strategy

* **Common** This layer contains useful methods about project specific features, things like Google Sign-In, Location Management, Unit management, ...

* **Final UI classes** Those classes are final UI displayed to the user (Weather, Forecast and Settings screens).

<p align="center"><img src="https://github.com/gahfy/chilindoweather/raw/master/img/UIDiagram.png" /></p>

### API Key and Keystore password

It was written in specifications that *A complete project (Android Studio) which **BUILDS** and runs without errors, generating an
application binary.*

This constraint forced me not to add the keystore folder in `.gitignore`. Without that constraint, the keystore directory would have been added to `.gitignore` and all security data (the below lines of `./app/gradle.properties`)would have been set in the `~/gradle.properties` file.

```groovy
open_weather_api_key="e609c501379535db475226cd8aad73fd"
key_store_password=chilindo
release_password=chilindorelease
```

### Use of `@SuppressWarnings` and `//noinspect`

The use of `@SuppressWarnings` and `//noinspect` allows to remove solve false positive warnings. It allows to have a 0 code smell on Sonar and no warning in Android Studio.

The goal by having 0 warning and code smells is to be able to easily detect new ones when writing new code, as Android Studio warns you when you commit, and you have the ability to check the number of code smells with Sonar.

If it is allowed to use them, they must all be prefixed with a comment explaining why it is safe to use them (typically starting by `//Safe because`)

### Logs

In order to allow override with Build configuration or adding Crashlytics, all log has done through `utils.logger` class.

Currently, here is what is added to log:
* Exceptions
* Retrofit requests and responses (below example of logs for a Retrofit request). They log url and headers for request, headers and content for response.
```
V/Retrofit: Requesting http://api.openweathermap.org/data/2.5/weather?lat=48.8400377&lon=2.2570947&appid=e609c501379535db475226cd8aad73fd
V/Rretrofit: {"coord":{"lon":2.26,"lat":48.84},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}],"base":"stations","main":{"temp":287.15,"pressure":1008,"humidity":85,"temp_min":286.15,"temp_max":288.15},"visibility":10000,"wind":{"speed":2.1,"deg":40},"clouds":{"all":0},"dt":1521981000,"sys":{"type":1,"id":5617,"message":0.003,"country":"FR","sunrise":1521956553,"sunset":1522001513},"id":3031137,"name":"Boulogne-Billancourt","cod":200}
V/Retrofit: ---------- Response Headers ----------
V/Retrofit: Access-Control-Allow-Credentials: true
V/Retrofit: Access-Control-Allow-Methods: GET, POST
V/Retrofit: Access-Control-Allow-Origin: *
V/Retrofit: Connection: keep-alive
V/Retrofit: Content-Length: 454
V/Retrofit: Content-Type: application/json; charset=utf-8
V/Retrofit: Date: Sun, 25 Mar 2018 12:58:51 GMT
V/Retrofit: Server: openresty
V/Retrofit: X-Cache-Key: /data/2.5/weather?lat=48.84&lon=2.26
V/Retrofit: ---------- Response Headers ----------
```

## Continuous Integration

### Travis

[![Build Status](https://travis-ci.org/gahfy/chilindoweather.svg?branch=master)](https://travis-ci.org/gahfy/chilindoweather)

Travis CI is used for continuous integration. It mainly checks that:

* The application builds in debug and release
* The unit tests are working in debug and release
* The instrumented tests are working in debug and release

After checking all of the above, Travis generates Jacoco report to get Code Coverage, and sends it to codecov.io.

It allows also to display result of build on a pull request, so it is more easy to detect if something fails before validating a pull request.

### Codecov.io

[![Codecov](https://codecov.io/github/gahfy/chilindoweather/coverage.svg?branch=master)](https://codecov.io/gh/gahfy/chilindoweather)

Codecov receives the code coverage report from Travis, and allows to display it nicely for the project.

<p align="center"><a href="https://codecov.io/gh/gahfy/chilindoweather"><img src="https://codecov.io/gh/gahfy/chilindoweather/branch/master/graphs/sunburst.svg" /></a></p>

<p align="center"><i>Code coverage sunburst</i></p>

It allows also to display difference of code coverage when a pull request is made, so it is more easy to detect if there is a lack of unit tests.

### SonarQube

[![SonarCloud](https://sonarcloud.io/api/badges/gate?key=chilindoweather%3Aapp)](https://sonarcloud.io/dashboard?id=chilindoweather%3Aapp)
[![SonarCloud](https://sonarcloud.io/api/badges/measure?key=chilindoweather%3Aapp&metric=coverage)](https://sonarcloud.io/dashboard?id=chilindoweather%3Aapp)
[![SonarCloud](https://sonarcloud.io/api/badges/measure?key=chilindoweather%3Aapp&metric=code_smells)](https://sonarcloud.io/dashboard?id=chilindoweather%3Aapp)
[![SonarCloud](https://sonarcloud.io/api/badges/measure?key=chilindoweather%3Aapp&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=chilindoweather%3Aapp)
[![SonarCloud](https://sonarcloud.io/api/badges/measure?key=chilindoweather%3Aapp&metric=ncloc)](https://sonarcloud.io/dashboard?id=chilindoweather%3Aapp)
[![SonarCloud](https://sonarcloud.io/api/badges/measure?key=chilindoweather%3Aapp&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=chilindoweather%3Aapp)
[![SonarCloud](https://sonarcloud.io/api/badges/measure?key=chilindoweather%3Aapp&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=chilindoweather%3Aapp)
[![SonarCloud](https://sonarcloud.io/api/badges/measure?key=chilindoweather%3Aapp&metric=security_rating)](https://sonarcloud.io/dashboard?id=chilindoweather%3Aapp)
[![SonarCloud](https://sonarcloud.io/api/badges/measure?key=chilindoweather%3Aapp&metric=sqale_index)](https://sonarcloud.io/dashboard?id=chilindoweather%3Aapp)
[![SonarCloud](https://sonarcloud.io/api/badges/measure?key=chilindoweather%3Aapp&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=chilindoweather%3Aapp)
[![SonarCloud](https://sonarcloud.io/api/badges/measure?key=chilindoweather%3Aapp&metric=alert_status)](https://sonarcloud.io/dashboard?id=chilindoweather%3Aapp)

SonarQube measures code quality and provide issue tracking.

## Libraries

### Application libraries

#### Retrofit 2

Retrofit is used to perform HTTP request. It is used instead of Volley for the following reasons:

* Extensibility: which allows use of many extension for deserialization is needed

#### Moshi

Moshi is used to deserialize Json into POJO instances.

#### RxJava / RxAndroid

RxJava allows to subscribe to end of asynchronous task. It is only used with Retrofit in this project. The main advantage of RxJava against Callable is the ability to use operators such as `map()` or `zip()` event if it is not used in this project.

#### Dagger 2

Dagger 2 allows to nicely manage dependency injection. It is used in order to deal with `Context` related classes in presenters.

#### Firebase

Firebase is included in order to provide Google Authentication which is required by the specification.

#### Glide

Glide is used to get image from internet (profile picture) and to deal with big image loading (background of the header of `NavigationDrawer`).

#### Timber

Timber is used to display logs in the application.

### Unit test Libraries

#### Mockito

As we use `Context` related objects in presenters, we use Mockito in unit tests in order to mock instances of those objects.

#### Gson

Actually, Json deserialization is performed by Moshi, but Gson is used in order to use `TypeToken` in JSON deserialization.

## Not done

### Technical Debt

Code coverage is only a little more than 30% because I didn't have enough time to do more. In order not to make Sonar fail, I used a custom quality gate which would have to be updated in the future as the code coverage increases.
