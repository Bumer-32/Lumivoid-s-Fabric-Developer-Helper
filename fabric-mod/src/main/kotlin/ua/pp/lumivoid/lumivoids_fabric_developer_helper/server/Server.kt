package ua.pp.lumivoid.lumivoids_fabric_developer_helper.server

import ua.pp.lumivoid.lumivoids_fabric_developer_helper.Constants
import ua.pp.lumivoid.lumivoids_fabric_developer_helper.util.Config
import java.net.InetAddress
import java.net.ServerSocket
import java.net.SocketException
import kotlin.concurrent.thread

object Server {
    private val logger = Constants.LOGGER

    private var serverSocket: ServerSocket? = null
    private val clients = mutableListOf<ClientHandler>()
    private var serverActive = true


    fun setup() {
        logger.info("Starting internal server...")
        var ip = Config.readConfig().ip
        val port = Config.readConfig().port

        serverSocket = ServerSocket(port, 50, InetAddress.getByName(ip))
        logger.info("Server started on $ip:$port")

        thread {
            while (serverActive) {
                try {
                    val client = serverSocket!!.accept()

                    logger.info("New client connected: ${client.inetAddress}")

                    val clientHandler = ClientHandler(client, this)
                    clients.add(clientHandler)

                    thread {
                        clientHandler.run()
                    }
                } catch (_: SocketException) {
                    logger.info("Socket closed")
                }
            }
            logger.info("Internal server stopped")
        }
    }

    fun shutdown() {
        logger.info("Shutting down internal server...")
        clients.toList().forEach {
            it.shutdown()
        }
        serverActive = false
        logger.info("Shutting down socket")
        serverSocket?.close()
    }

    fun removeClient(client: ClientHandler) {
        clients.remove(client)
    }
}