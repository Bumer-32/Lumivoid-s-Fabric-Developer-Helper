package ua.pp.lumivoid.lumivoids_fabric_developer_helper.server

import net.minecraft.client.MinecraftClient
import ua.pp.lumivoid.lumivoids_fabric_developer_helper.Constants
import ua.pp.lumivoid.lumivoids_fabric_developer_helper.Tasks
import java.net.Socket
import java.util.Scanner

class ClientHandler(client: Socket?, private val server: Server) {
    private val logger = Constants.LOGGER

    private val client: Socket = client!!
    private val reader: Scanner = Scanner(this.client.getInputStream())
    private var running = false

    fun run() {
        running = true
        logger.info("Client ${client.inetAddress} connected")

        while (running) {
            try {
                val data = reader.nextLine()

                logger.info("Received $data from ${client.inetAddress}")

                Tasks.entries.forEach {
                    if (data.lowercase() == it.toString().lowercase().replace("_", "")) {
                        MinecraftClient.getInstance().submit { // In RENDER THREAD!!! IT'S VERY IMPORTANT
                            it.run()
                        }
                    }
                }

            } catch (_: Exception) {
                logger.warn("Connection lost with ${client.inetAddress}")
                shutdown()
                break
            }
        }
    }

    fun shutdown() {
        logger.info("Shutting down client ${client.inetAddress}")
        running = false
        client.close()
        server.removeClient(this)
    }
}