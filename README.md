# Main Tracker
A Runelite Plugin that allows you to track your friends' locations and progress in realtime.

## Java Dependency
JDK 11 - Seems to be the only JDK version that works for Runelite plugin development.

## Build Instructions - Eclipse (MacOS)
#### Configuring your project's run configurations:
1. Import the project into Eclipse as an Existing Gradle Project.
2. Right click project -> Run > Run Configurations.
3. Set the Main class as com.example.ExamplePluginTest. # TODO: Update when the package and main class changes
4. In Arguments > VM Arguments, add the `-ea` option.
5. In JRE > Alternate JRE, Select JDK11 from the dropdown menu.
6. Apply and save changes.

#### Configuring your Java compiler:
1. Find Eclipse menu > Settings > Java > Compiler.
2. Ensure that Compiler compliance level is 11.
3. Apply and save changes.
