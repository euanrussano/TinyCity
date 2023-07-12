package com.sophia.tinycity.model

import com.badlogic.gdx.math.MathUtils
import com.sophia.tinycity.infrastructure.XMLBuildingRepository
import com.sophia.tinycity.infrastructure.Repository
import com.sophia.tinycity.infrastructure.TerrainRepository

class GameFacade {

    private lateinit var model : GameModel

    val buildingRepository : Repository<Building> = XMLBuildingRepository()
    val terrainRepository : Repository<Terrain> = TerrainRepository()

    var buildingToPlace: String? = null

    fun createSimpleGame() {
        val tower = buildingRepository.findByName("Tower")
        val towerInstance = BuildingInstance(tower)

        val lowGrassTerrain = terrainRepository.findByName("Low Grass")
        val highGrassTerrain = terrainRepository.findByName("High Grass")
        val waterTerrain = terrainRepository.findByName("Shallow Water")

        val width = 20
        val height = 9
        val tiles = Array(width){ x ->
            Array(height){ y ->
                var terrain = lowGrassTerrain
                if (x == 5) terrain = waterTerrain
                else if (MathUtils.random() < 0.5f) terrain = highGrassTerrain
                Tile(terrain, x, y)
            }
        }
        tiles[4][4].buildingInstance = towerInstance

        val map = TileMap(tiles)
        model = GameModel(map)
    }

    fun placeBuilding(tile: Tile) {
        if (buildingToPlace == null) return
        val building : Building = buildingRepository.findByName(buildingToPlace!!)
        val buildingInstance = BuildingInstance(building)
        tile.buildingInstance = buildingInstance

    }

    fun endTurn(){
        // reset the resources per turn
        model.resourcesPerTurn.replaceAll { _, _ -> 0 }
        // loop over the buildings and update resources
        model.map.tiles.forEach { row ->
            row.forEach { tile ->
                val buildingInstance = tile.buildingInstance ?: return@forEach
                val resourceName = buildingInstance.Building.prodResource
                model.resources[resourceName] = model.resources[resourceName]!! + tile.buildingInstance?.Building!!.prodResourceAmount
                model.resourcesPerTurn[resourceName] = model.resourcesPerTurn[resourceName]!! + tile.buildingInstance?.Building!!.prodResourceAmount
            }

        }

        model.turn += 1
    }

    fun getTiles(): Array<Array<Tile>> {
        return model.map.tiles
    }

    fun getTileAt(x: Int, y: Int): Tile? {
        return model.map.getTileAt(x, y)
    }

    fun getData(): GameModel {
        return model
    }
}