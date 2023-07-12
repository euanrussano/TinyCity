package com.sophia.tinycity.asset

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import ktx.assets.toInternalFile


class TopDownAssets : Assets {

    private var grassAtlas: TextureAtlas
    private var shoreAtlas: TextureAtlas
    private val userInterfaceSheets = mutableMapOf<String, Array<Array<TextureRegion>>>()
    private val terrainSheets = mutableMapOf<String, Array<Array<TextureRegion>>>()
    private val buildingSheets =  mutableMapOf<String, Array<Array<TextureRegion>>>()

    init {
        val buildingFiles = Gdx.files.local("assets/texture/Building/Wood/").list()
        buildingFiles.forEach { file ->
            buildingSheets[file.name().split(".")[0]] = TextureRegion(Texture(file)).split(16,16)
        }

        val terrainFiles = Gdx.files.local("assets/texture/Ground/").list()
        terrainFiles.forEach { file ->
            if (!file.name().endsWith("png")) return@forEach
            terrainSheets[file.name().split(".")[0]] = TextureRegion(Texture(file)).split(16,16)
        }
        val userInterfaceFiles = Gdx.files.local("assets/User Interface/").list()
        userInterfaceFiles.forEach { file ->
            userInterfaceSheets[file.name().split(".")[0]] = TextureRegion(Texture(file)).split(16,16)
        }

        shoreAtlas = TextureAtlas("texture/Ground/Shore.atlas".toInternalFile())
        grassAtlas = TextureAtlas("texture/Ground/Grass.atlas".toInternalFile())

    }


    override fun getTerrainTexture(name: String): TextureRegion {
        val nameRegion = name.replace(" ", "-")
        val shoreRegion = shoreAtlas.findRegion(nameRegion)
        if (shoreRegion != null) return shoreRegion

        val groundRegion = grassAtlas.findRegion(nameRegion)
        if (groundRegion != null) return groundRegion

        throw Error("Terrain Texture for $nameRegion not found")

    }

    override fun getBuildingTextureSheet(buildingName: String): Array<Array<TextureRegion>> {
        return buildingSheets[buildingName] ?: throw Error("Building Texture sheet for $buildingName not found")
    }

    override fun getTerrainTextureSheet(terrainName: String): Array<Array<TextureRegion>> {
        return terrainSheets[terrainName] ?: throw Error("Terrain Texture sheet for $terrainName not found")
    }

    override fun getUserInterfaceSheet(name: String): Array<Array<TextureRegion>> {
        return userInterfaceSheets[name] ?: throw Error("UI Texture sheet for $name not found")
    }

}
