package com.sophia.tinycity.model

class GameModel(val map: TileMap) {



    val resources = mutableMapOf(
        "Food" to 0,
        "Population" to 0,
        "Metal" to 0,
        "Wood" to 0,
    )
    val resourcesPerTurn = mutableMapOf(
        "Food" to 0,
        "Population" to 0,
        "Metal" to 0,
        "Wood" to 0,
    )

    var turn = 0



    companion object{

    }
}
