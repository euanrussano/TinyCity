package com.sophia.tinycity.infrastructure

import com.badlogic.gdx.utils.XmlReader
import com.sophia.tinycity.model.Building
import ktx.assets.toInternalFile

class XMLBuildingRepository : Repository<Building>() {

    init {
        val xmlReader = XmlReader()
        val parse = xmlReader.parse("data/buildings.xml".toInternalFile())
        for (i in 0 until parse.childCount){
            val building = parse.getChild(i)
            val buildingName = building.get("name")
            val resourcesProduced = building.getChildByName("resourcesProduced")
            val resources = resourcesProduced.getChildrenByName("resource")
            var prodResource = "Food"
            var prodResourceAmount = 0
            for (resource in resources){
                prodResource = resource.get("name")
                prodResourceAmount = resource.get("amount").toInt()
            }

            entities.add(Building(buildingName, prodResource, prodResourceAmount))
        }


    }
    override fun getName(entity: Building): String {
        return entity.name
    }
}