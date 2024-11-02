package ua.pp.lumivoid.gradle.lumivoids_fabric_developer_helper

import org.gradle.api.Plugin
import org.gradle.api.Project
import java.net.Socket

@Suppress("unused")
class LumivoidsFabricDeveloperPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val extensionOptions = project.extensions.create("lumivoidsDev", LumivoidsDevExtension::class.java)

        project.repositories.maven {
            it.name = "Lumivoid"
            it.setUrl("https://maven.lumivoid.pp.ua/repository/lumivoid/")
        }

        project.dependencies.add("modRuntimeOnly", "ua.pp.lumivoid:lumivoids-fabric-developer-helper-mod:${Constants.PLUGIN_VERSION}")

        project.tasks.register("reloadThisScreen") {
            it.group = Constants.GRADLE_GROUP
            it.description = "Reloads current opened screen"

            it.doLast {
                connectAndSend(extensionOptions, "reloadThisScreen")
            }
        }

        project.tasks.named("classes").configure {
            it.finalizedBy("reloadThisScreen")
        }
    }

    private fun connectAndSend(options: LumivoidsDevExtension, message: String) {
        try {
            Socket(options.minecraftIP, options.minecraftPort).use { socket ->
                val output = socket.outputStream.bufferedWriter()
                println("Connected to minecraft")

                output.write(message)
                output.flush()
                println("Sent message: $message")

                output.close()
                socket.close()
            }
        } catch (e: Exception) {
            println("Failed to connect to minecraft")
            e.printStackTrace()
        }
    }
}