package com.sophia.tinycity.asset

import com.badlogic.gdx.graphics.g2d.TextureRegion

interface Assets {

    fun getTerrainTexture(name: String): TextureRegion
    fun getBuildingTextureSheet(buildingName: String): Array<Array<TextureRegion>>
    fun getTerrainTextureSheet(terrainName: String): Array<Array<TextureRegion>>
    fun getUserInterfaceSheet(name: String): Array<Array<TextureRegion>>

}
