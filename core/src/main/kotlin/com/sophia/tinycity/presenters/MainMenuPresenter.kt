package com.sophia.tinycity.presenters

import com.badlogic.gdx.Gdx
import com.sophia.tinycity.MainMenuScreen
import com.sophia.tinycity.model.GameFacade
import kotlin.system.exitProcess

class MainMenuPresenter(val model: GameFacade) {

    private lateinit var screen: MainMenuScreen

    fun onAttach(mainMenuScreen: MainMenuScreen) {
        this.screen = mainMenuScreen
    }

    fun onNewGame() {
        model.createSimpleGame()
        screen.changeToGameScreen()
    }

    fun onLoadGame() {
        TODO("Not yet implemented")
    }

    fun onPreferences() {
        TODO("Not yet implemented")
    }

    fun onExitGame() {
        Gdx.app.exit()
        exitProcess(0)
    }


}
