# Main Tracker
A Runelite Plugin that allows you to track your friends' locations and progress in realtime.

## Java Dependency
JDK 11 — seems to be the only JDK version that works for Runelite plugin development.

On macOS with Homebrew:
```bash
brew install openjdk@11
/usr/libexec/java_home -v 11   # print install path for IDE configuration
```

## Running the client
The RuneLite dev client entry point is `mainTrackerTest.MainTrackerPluginTest` (`src/test/java/mainTrackerTest/MainTrackerPluginTest.java`). Use VM argument `-ea` (enable assertions) when launching.

## Build Instructions — VS Code (macOS)

### Prerequisites
1. Install [JDK 11](#java-dependency).
2. Install [VS Code](https://code.visualstudio.com/) and the [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack).

### Configure JDK 11
1. Open this folder in VS Code (**File → Open Folder**).
2. Command Palette (`Cmd+Shift+P`) → **Java: Configure Java Runtime**.
3. Set **JDK 11** as the default for this workspace (or add a runtime and mark it default).

Alternatively, add `.vscode/settings.json` with your JDK 11 path (adjust for your machine):
```json
{
  "java.jdt.ls.java.home": "/path/to/jdk-11",
  "java.configuration.runtimes": [
    {
      "name": "JavaSE-11",
      "path": "/path/to/jdk-11",
      "default": true
    }
  ]
}
```

Homebrew OpenJDK 11 is often at:
`/opt/homebrew/opt/openjdk@11/libexec/openjdk.jdk/Contents/Home`

### Import the project
1. Open this repository as a folder; the Java extension imports it as a Gradle project automatically.
2. Wait for Gradle sync / “Building workspace” to finish in the status bar.
3. If import fails: **Java: Clean Java Language Server Workspace** → reload the window, then retry.

Compiler level 11 is set in `build.gradle` (`options.release.set(11)`).

### Run / debug RuneLite
Use the included launch configuration (**Run and Debug** → **Run RuneLite (Main Tracker)**), or open `MainTrackerPluginTest.java` and use **Run** / **Debug** above `main`.

The launch config (`.vscode/launch.json`) sets:
- **Main class:** `mainTrackerTest.MainTrackerPluginTest`
- **VM args:** `-ea`
- **projectName:** must match `rootProject.name` in `settings.gradle` (currently `example`)

### Build the plugin JAR
```bash
./gradlew shadowJar
```
Output: `build/libs/example-1.0-SNAPSHOT-all.jar`

## Build Instructions — Eclipse (macOS)

### Configuring your project's run configurations
1. Import the project into Eclipse as an Existing Gradle Project.
2. Right click project → **Run → Run Configurations**.
3. Set the main class to `mainTrackerTest.MainTrackerPluginTest`.
4. Under **Arguments → VM arguments**, add `-ea`.
5. Under **JRE → Alternate JRE**, select JDK 11.
6. Apply and save.

### Configuring your Java compiler
1. **Eclipse → Settings → Java → Compiler**.
2. Set **Compiler compliance level** to **11**.
3. Apply and save.
