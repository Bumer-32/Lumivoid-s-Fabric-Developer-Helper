package ua.pp.lumivoid.lumivoids_fabric_developer_helper

import net.fabricmc.loader.api.FabricLoader
import org.slf4j.LoggerFactory

object Constants {
    const val MOD_ID = "lumivoids-fabric-developer-helper"
    val LOGGER = LoggerFactory.getLogger(MOD_ID)
    val CONFIG_FOLDER = "${FabricLoader.getInstance().configDir}\\$MOD_ID"
    val CONFIG_FILE = CONFIG_FOLDER + "\\$MOD_ID.json"
}