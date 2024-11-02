package ua.pp.lumivoid.lumivoids_fabric_developer_helper

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents
import org.slf4j.LoggerFactory
import ua.pp.lumivoid.lumivoids_fabric_developer_helper.server.Server

object LumivoidsFabricDeveloperHelper : ModInitializer {
    private val logger = LoggerFactory.getLogger("lumivoids-fabric-developer-helper")

	override fun onInitialize() {
		logger.info("${Constants.MOD_ID} loaded!")
		Server.setup()

		ClientLifecycleEvents.CLIENT_STOPPING.register {
			Server.shutdown()
		}
	}
}