package com.sophia.tinycity.model

class Tile(
    val terrain : Terrain,
    var x : Int,
    var y : Int
) {

    var startTile = false
    var buildingInstance : BuildingInstance? = null
    var canPlaceBuilding = false




}
