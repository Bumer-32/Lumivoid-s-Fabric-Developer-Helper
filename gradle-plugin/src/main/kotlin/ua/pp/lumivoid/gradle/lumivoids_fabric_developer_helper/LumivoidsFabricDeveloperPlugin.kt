package ua.pp.lumivoid.gradle.lumivoids_fabric_developer_helper

import org.gradle.api.Plugin
import org.gradle.api.Project
import java.net.Socket

@Suppress("unused")
class LumivoidsFabricDeveloperPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val extensionOptions = project.extensions.create("lumivoidsDev", LumivoidsDevExtension::class.java)

        project.dependencies.add("modRuntimeOnly", "ua.pp.lumivoid:lumivoids-fabric-developer-helper-mod:${Constants.PLUGIN_VERSION}")

        project.tasks.register("reloadThisScreen") {
            it.group = Constants.GRADLE_GROUP
            it.description = "Reloads current opened screen"

            it.doLast {
                connectAndSend(extensionOptions, "reloadThisScreen")
            }
        }
    }


    private fun connectAndSend(options: LumivoidsDevExtension, message: String) {
        Socket(options.minecraftIP, options.minecraftPort).use { socket ->
            val output = socket.outputStream.bufferedWriter()
            println("Connected to minecraft")

            output.write(message)
            output.flush()
            println("Sent message: $message")

            output.close()
            socket.close()
        }
    }
}