package com.sophia.tinycity

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.sophia.tinycity.presenters.GamePresenter
import com.sophia.tinycity.view.BuildingActor
import com.sophia.tinycity.view.TileActor
import ktx.actors.onClick
import ktx.actors.onClickEvent
import ktx.app.clearScreen
import ktx.assets.disposeSafely
import ktx.scene2d.actors
import ktx.scene2d.button
import ktx.scene2d.image
import ktx.scene2d.label
import ktx.scene2d.scene2d
import ktx.scene2d.table
import ktx.scene2d.textButton

class GameScreen(val game : TinyCityGame) : Screen {

    private lateinit var resourcesTable: Table
    private lateinit var turnLabel: Label
    private lateinit var presenter: GamePresenter
    private lateinit var buildingsTable: Table
    val mainStage = Stage(ExtendViewport(20 * Constants.PPM, 9 * Constants.PPM))
    val uiStage = Stage(ExtendViewport(Constants.UI_VIEWPORT_WIDTH, Constants.UI_VIEWPORT_HEIGHT))

    override fun show() {

        presenter = GamePresenter(game.model)

        mainStage.viewport.camera.position.add(0f, -Constants.PPM *4f, 0f)
        mainStage.viewport.camera.update()
        val ppm = Constants.PPM
        val tilesGroup = Group()
        tilesGroup.name = "Tiles"
        mainStage.addActor(tilesGroup)

        setupUI()


        val im = InputMultiplexer()
        im.addProcessor(uiStage)
        im.addProcessor(mainStage)
        Gdx.input.inputProcessor = im

        presenter.onAttach(this)
    }

    private fun setupUI() {
        val ppm = Constants.PPM

        val pixmap = Pixmap(1, 1, Pixmap.Format.RGBA8888)
        pixmap.setColor(Color.DARK_GRAY)
        pixmap.fill()
        val tableBackgroundTexture = TextureRegionDrawable(Texture(pixmap))
        pixmap.dispose()

        uiStage.actors{
            table {
                setFillParent(true)
                // main content
                table {
                    it.grow()
                }
                row()
                // bottom bar
                table {
                    it.growX()
                    background = tableBackgroundTexture
                    defaults().pad(Constants.PPM /2f)

                    table{
                        it.growX()
                        defaults().pad(Constants.PPM /10f)
                        this.left()
                        this@GameScreen.buildingsTable = this
                    }

                    table{
                        defaults().pad(ppm/5f)
                        this.left()
                        defaults().left()

                        this@GameScreen.resourcesTable = this
                        label("Food:")
                        label("25 (+80)")
                        label("Metal:")
                        label("3 (+2)")
                        row()
                        label("Wood:")
                        label("25 (+80)")
                        label("Population:")
                        label("3 (+0)")

                    }
                    label("Turn 257") { this@GameScreen.turnLabel = this }
                    textButton("End Turn"){
                        pad(Constants.PPM /2f)
                        onClick {
                            onClickEndTurnButton()
                        }
                    }
                }
            }
        }
    }

    private fun onClickEndTurnButton() {
        presenter.endTurn()
    }

    private fun onClickBuildingButton(buildingName: String) {
        presenter.onSelectBuilding(buildingName)

    }

    fun toggleBuildingsTable(toggle : Boolean){
        this.buildingsTable.isVisible = toggle
    }

    override fun render(delta: Float) {
        uiStage.act(delta)
        mainStage.act(delta)

        clearScreen(red = 0.7f, green = 0.7f, blue = 0.7f)

        mainStage.draw()
        uiStage.draw()

    }

    override fun resize(width: Int, height: Int) {
        mainStage.viewport.update(width, height)
        uiStage.viewport.update(width, height)
    }

    override fun pause() {
        TODO("Not yet implemented")
    }

    override fun resume() {
        TODO("Not yet implemented")
    }

    override fun hide() {
        TODO("Not yet implemented")
    }

    override fun dispose() {
        mainStage.disposeSafely()
        uiStage.disposeSafely()
    }

    fun disableHighlight() {
        val tiles = mainStage.root.findActor<Group>("Tiles")
        tiles.children.forEach { actor ->
            (actor as TileActor).toggleHighlight(false)
        }
    }

    fun addTileActor(terrainName : String, modelX: Int, modelY: Int) {
        val ppm = Constants.PPM
        val tiles = mainStage.root.findActor<Group>("Tiles")
        tiles.addActor(TileActor(game.assets, terrainName).apply {
            setPosition(modelX*ppm, modelY*ppm)
            onClickEvent { event, x, y ->
                presenter.onClickTile(modelX, modelY)
            }
        })

    }

    fun addBuildingActor(buildingName : String, modelX: Int, modelY: Int) {
        val ppm = Constants.PPM
        val stageX = modelX*ppm
        val stageY = modelY*ppm
        val buildingActor = BuildingActor(game.assets, buildingName)
        val tiles = mainStage.root.findActor<Group>("Tiles")
        tiles.children.forEach{ tileActor ->
            if (tileActor.x == stageX && tileActor.y == stageY){
                (tileActor as Group).addActor(buildingActor)
            }
        }


    }

    fun highlightTile(modelX: Int, modelY: Int) {
        val ppm = Constants.PPM
        val stageX = modelX*ppm
        val stageY = modelY*ppm
        val tiles = mainStage.root.findActor<Group>("Tiles")
        tiles.children.forEach{ tileActor ->
            if (tileActor.x == stageX && tileActor.y == stageY){
                (tileActor as TileActor).toggleHighlight(true)
            }
        }
    }

    fun addBuildingButton(name: String) {
        this.buildingsTable.add(
            scene2d.button {
                val texture = game.assets.getBuildingTextureSheet(name)[0][0]
                image(texture){
                    it.size(Constants.PPM)
                }
                onClick {
                    this@GameScreen.onClickBuildingButton(name)
                }
            }
        )
    }

    fun updateTurnText(turnText: String) {
        turnLabel.setText("Turn: $turnText")
    }

    fun updateResourcesTable(resources: Map<String, String>, resourcesPerTurn: Map<String, String>) {
        this.resourcesTable.clear()
        this.resourcesTable.add(scene2d.label("Food:"))
        this.resourcesTable.add(scene2d.label(resources["Food"]!! + " (+" + resourcesPerTurn["Food"] + ")" ){ color = Color.ORANGE})
        this.resourcesTable.add(scene2d.label("Wood:"))
        this.resourcesTable.add(scene2d.label(resources["Wood"]!! + " (+" + resourcesPerTurn["Wood"] + ")" ){ color = Color.ORANGE})
        this.resourcesTable.row()
        this.resourcesTable.add(scene2d.label("Metal:"))
        this.resourcesTable.add(scene2d.label(resources["Metal"]!! + " (+" + resourcesPerTurn["Metal"] + ")" ){ color = Color.ORANGE})
        this.resourcesTable.add(scene2d.label("Population:"))
        this.resourcesTable.add(scene2d.label(resources["Population"]!! + " (+" + resourcesPerTurn["Population"] + ")" ){ color = Color.ORANGE})
    }


}