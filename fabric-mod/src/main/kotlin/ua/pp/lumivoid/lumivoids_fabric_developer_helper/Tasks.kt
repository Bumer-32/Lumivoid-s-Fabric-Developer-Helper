package ua.pp.lumivoid.lumivoids_fabric_developer_helper

import net.minecraft.client.MinecraftClient

enum class Tasks {
    RELOAD_THIS_SCREEN {
        override fun run() {
            logger.info("Reloading this screen")

            MinecraftClient.getInstance().setScreen(null)
            MinecraftClient.getInstance().setScreen(Options.currentMinecraftScreen)
        }
    };
    protected val logger = Constants.LOGGER
    abstract fun run()
}