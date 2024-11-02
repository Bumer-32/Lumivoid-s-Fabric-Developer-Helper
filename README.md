Mod and gradle plugin which helps with developing mods


Now there almost no features, so using it almost useless.

There's no documentation now but here how to add it to your project:

-in settings.gradle
```groovy
pluginManagement {
	repositories {
		maven {
			name = 'Lumivoid'
			url = 'https://maven.lumivoid.pp.ua/repository/lumivoid/'
		}
	}
}
```

-in build.gradle
```groovy
plugins {
    id 'ua.pp.lumivoid.gradle.lumivoids_fabric_developer_helper.lumivoids-fabric-developer-helper-plugin' version '1.0.2'
}
```