package com.sophia.tinycity.presenters

import com.badlogic.gdx.Gdx
import com.sophia.tinycity.GameScreen
import com.sophia.tinycity.model.GameFacade
import com.sophia.tinycity.model.Tile

class GamePresenter(val model: GameFacade) {

    private lateinit var screen : GameScreen

    fun onAttach(screen: GameScreen) {
        this.screen = screen

        // configure view to show the world map
        model.getTiles().forEach { row ->
            row.forEach { tile ->
                screen.addTileActor(tile.terrain.name, tile.x, tile.y)
                if (tile.buildingInstance != null){
                    showBuildingInstance(tile)
                }
            }
        }

        // configure view to show buildings buttons
        model.buildingRepository.findAll().forEach{ building ->
            screen.addBuildingButton(building.name)
        }

        updateUI()

    }

    private fun showBuildingInstance(tile: Tile) {
        if (tile.buildingInstance == null){
            Gdx.app.log(this::class.java.simpleName, "Tried to show building but is null for tile ${tile.x}, ${tile.y}")
            return
        }
        val buildingInstance = tile.buildingInstance!!
        screen.addBuildingActor(buildingInstance.Building.name, tile.x, tile.y)

    }

    fun onClickTile(x : Int, y : Int) {
        val tile = model.getTileAt(x, y) ?: return
        if (tile.buildingInstance == null && tile.canPlaceBuilding && model.buildingToPlace != null){
            model.placeBuilding(tile)
            showBuildingInstance(tile)
            disableAllTiles()
        }
    }


    fun endTurn() {
        model.endTurn()
        screen.toggleBuildingsTable(true)

        updateUI()
    }

    private fun updateUI() {
        val gamedata = model.getData()
        screen.updateTurnText(gamedata.turn.toString())

        val resources = gamedata.resources.map { entry -> entry.key to entry.value.toString() }.toMap()
        val resourcesPerTurn = gamedata.resourcesPerTurn.map { entry -> entry.key to entry.value.toString() }.toMap()
        screen.updateResourcesTable(resources, resourcesPerTurn)
    }

    fun onSelectBuilding(buildingName: String) {
        screen.toggleBuildingsTable(false)
        model.buildingToPlace = buildingName
        highlightAvailableTiles()
    }

    fun highlightAvailableTiles() {
        model.getTiles().forEach { row ->
            row.forEach {  tile ->
                if (tile.buildingInstance == null) return@forEach
                val x = tile.x
                val y = tile.y
                val tilesAdjacent = mutableListOf(
                    model.getTileAt(x, y+1),
                    model.getTileAt(x, y-1),
                    model.getTileAt(x+1, y),
                    model.getTileAt(x-1, y)
                )
                tilesAdjacent.forEach { tile2 ->
                    if (tile2 != null){
                        if (tile2.buildingInstance == null){
                            tile2.canPlaceBuilding = true
                            screen.highlightTile(tile2.x, tile2.y)
                        }
                    }
                }
            }
        }
    }

    private fun disableAllTiles()
    {
        model.getTiles().forEach { row ->
            row.forEach { tile ->
                tile.canPlaceBuilding = false
            }
        }
        screen.disableHighlight()
    }
}
