package ua.pp.lumivoid.gradle.lumivoids_fabric_developer_helper


object Constants {
    const val GRADLE_GROUP = "Minecraft developing things"
    val PLUGIN_VERSION = this.javaClass.getResource("/version.txt")?.readText()
}