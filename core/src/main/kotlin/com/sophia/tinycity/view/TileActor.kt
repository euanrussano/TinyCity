package com.sophia.tinycity.view

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.sophia.tinycity.Constants
import com.sophia.tinycity.asset.Assets
import ktx.assets.toInternalFile


class TileActor(assets : Assets, terrainName : String) : Group() {

    private var highlight: Image

    init {
        val ppm = Constants.PPM

        val highlightBoxes = assets.getUserInterfaceSheet("Highlighted-Boxes")
        val grassTexture = assets.getTerrainTexture(terrainName)

        val pixmap = Pixmap(64, 64, Pixmap.Format.RGBA8888)
        pixmap.setColor(Color.FOREST)
        pixmap.drawRectangle(0,0,64,64)
        val groundBorder = Texture(pixmap)
        pixmap.dispose()

        val groundActor = Image(grassTexture)
        groundActor.setFillParent(true)
        addActor(groundActor)

        val groundBorderActor = Image(groundBorder)
        groundBorderActor.setFillParent(true)
        addActor(groundBorderActor)

        highlight = Image(highlightBoxes[0][0])
        highlight.setFillParent(true)
        highlight.isVisible = false
        addActor(highlight)


        setSize(ppm, ppm)

        //tilePresenter.onAttach(this)

    }

    fun toggleHighlight(toggle : Boolean){
        highlight.isVisible = toggle
    }
}