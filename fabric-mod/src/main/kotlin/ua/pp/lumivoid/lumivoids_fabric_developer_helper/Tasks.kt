package ua.pp.lumivoid.lumivoids_fabric_developer_helper

import net.minecraft.client.MinecraftClient

enum class Tasks {
    RELOAD_THIS_SCREEN {
        override fun run() {
            logger.info("Reloading this screen")

            val currScreen = MinecraftClient.getInstance().currentScreen
            MinecraftClient.getInstance().setScreen(null)
            MinecraftClient.getInstance().setScreen(currScreen)
        }
    };
    protected val logger = Constants.LOGGER
    abstract fun run()
}