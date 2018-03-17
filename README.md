# Chilindo OpenWeatherMap Application

## API Key and Keystore password

It was written in specifications that *A complete project (Android Studio) which **BUILDS** and runs without errors, generating an
application binary.*

This constraint forced me not to add the keystore folder in `.gitignore`. Without that constraint, the keystore directory would have been added to `.gitignore` and all security data would have been set in the **user** `gradle.properties` file instead of the app one.
