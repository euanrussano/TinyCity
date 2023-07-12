package com.sophia.tinycity.infrastructure

import com.sophia.tinycity.model.Building

class InMemoryBuildingRepository : Repository<Building>() {

    override val entities = mutableListOf(
        Building("House", "Population", 1),
        Building("Farm", "Food", 1),
        Building("Stone Mine", "Metal", 1),
        Building("Woodcutter", "Wood", 1),
        Building("Tower", "Population", 0)
    )

    override fun getName(entity: Building): String {
        return entity.name
    }


}
