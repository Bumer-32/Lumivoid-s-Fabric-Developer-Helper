package ua.pp.lumivoid.lumivoids_fabric_developer_helper.util

import kotlinx.serialization.Serializable

@Serializable
data class ConfigData(
    val ip: String,
    val port: Int,
)
