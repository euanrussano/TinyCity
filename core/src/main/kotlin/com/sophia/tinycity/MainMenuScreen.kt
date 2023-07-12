package com.sophia.tinycity

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.sophia.tinycity.presenters.MainMenuPresenter
import ktx.actors.onClick
import ktx.app.clearScreen
import ktx.assets.disposeSafely
import ktx.scene2d.actors
import ktx.scene2d.label
import ktx.scene2d.table
import ktx.scene2d.textButton

class MainMenuScreen(val game: TinyCityGame) : Screen {

    private lateinit var presenter: MainMenuPresenter
    val uiStage = Stage(ExtendViewport(Constants.UI_VIEWPORT_WIDTH, Constants.UI_VIEWPORT_HEIGHT))

    override fun show() {
        presenter = MainMenuPresenter(game.model)

        uiStage.actors {
            table {
                setFillParent(true)
                defaults().pad(5f)
                defaults().uniformX()
                label("Tiny City")
                row()
                textButton("New Game"){
                    pad(5f)
                    onClick {
                        presenter.onNewGame()
                    }
                }
                row()
                textButton("Load Game"){
                    pad(5f)
                    onClick {
                        presenter.onLoadGame()
                    }
                }
                row()
                textButton("Preferences"){
                    pad(5f)
                    onClick {
                        presenter.onPreferences()
                    }
                }
                row()
                textButton("Exit Game"){
                    pad(5f)
                    onClick {
                        presenter.onExitGame()
                    }
                }
            }
        }

        Gdx.input.inputProcessor = uiStage

        presenter.onAttach(this)
    }

    override fun render(delta: Float) {
        clearScreen(0f, 0f, 0f)
        uiStage.act()
        uiStage.draw()
    }

    override fun resize(width: Int, height: Int) {
        uiStage.viewport.update(width, height)
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun hide() {
        Gdx.input.inputProcessor = null
    }

    override fun dispose() {
        uiStage.disposeSafely()
    }

    fun changeToGameScreen() {
        game.screen = GameScreen(game)
    }


}
