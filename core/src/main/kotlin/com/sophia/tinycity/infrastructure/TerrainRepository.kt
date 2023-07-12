package com.sophia.tinycity.infrastructure

import com.sophia.tinycity.model.Terrain

class TerrainRepository : Repository<Terrain>() {

    override val entities = mutableListOf(
        Terrain("Low Grass"),
        Terrain("High Grass"),
        Terrain("Shallow Water")
    )

    override fun getName(entity: Terrain): String {
        return entity.name
    }

}
