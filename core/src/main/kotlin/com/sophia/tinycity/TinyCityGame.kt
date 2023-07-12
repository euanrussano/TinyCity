package com.sophia.tinycity

import com.badlogic.gdx.Game
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.sophia.tinycity.asset.Assets
import com.sophia.tinycity.asset.TopDownAssets
import com.sophia.tinycity.model.GameFacade
import com.sophia.tinycity.model.GameModel
import ktx.assets.toInternalFile
import ktx.scene2d.Scene2DSkin


class TinyCityGame(val testMode : Boolean ) : Game() {

    lateinit var assets : Assets
    lateinit var model : GameFacade

    override fun create() {
        MathUtils.random.setSeed(0)
        model = GameFacade()
        Scene2DSkin.defaultSkin = Skin("ui/uiskin.json".toInternalFile())
        assets = TopDownAssets()

        if (testMode){
            enterTestMode()
        } else {
            setScreen(MainMenuScreen(this))
        }
    }

    private fun enterTestMode() {
        // skip main menu and jump straight into the game
        // screen with a simple configuration
        model.createSimpleGame()
        setScreen(GameScreen(this))
    }
}

