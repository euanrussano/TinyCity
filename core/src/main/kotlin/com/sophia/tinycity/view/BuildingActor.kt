package com.sophia.tinycity.view

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import com.sophia.tinycity.Constants
import com.sophia.tinycity.asset.Assets

class BuildingActor(assets : Assets, buildingName : String) : Actor() {

    private val textureSheet: Array<Array<TextureRegion>>
    private val currentTextureRegion : TextureRegion

    init {
        val ppm = Constants.PPM

        textureSheet = assets.getBuildingTextureSheet(buildingName)
        currentTextureRegion = textureSheet[0][0]

        setSize(ppm*0.8f, ppm*0.8f)
        setPosition(0.1f*ppm, 0.1f*ppm)
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        batch.draw(currentTextureRegion, x, y, width, height)
    }

}
