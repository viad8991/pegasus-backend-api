package org.pegasus.backendapi.routepoint.entertainment

sealed interface Entertainment {
    val id: Long
    val name: String
    val city: String
}

data class Attraction(
    override val id: Long,
    override val name: String,
    override val city: String,
    val description: String
) : Entertainment

data class Restaurant(
    override val id: Long,
    override val name: String,
    override val city: String,
    val cuisine: String
) : Entertainment
