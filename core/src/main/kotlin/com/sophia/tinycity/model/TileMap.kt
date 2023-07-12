package com.sophia.tinycity.model

class TileMap(val tiles : Array<Array<Tile>>) {

    fun getTileAt(x: Int, y: Int): Tile? {
        if (x < 0 || x > tiles.size || y < 0 || y > tiles[0].size) return null
        return tiles[x][y]
    }


}
