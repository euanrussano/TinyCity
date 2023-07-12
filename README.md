# TinyCity

A [libGDX](https://libgdx.com/) project generated with [gdx-liftoff](https://github.com/tommyettinger/gdx-liftoff).

This project was generated with a template including simple application launchers and an `ApplicationAdapter` extension that draws libGDX logo.

## Overview:
Our simple city builder game allows players to design and build their own city from the ground up. Players will have access to a variety of buildings and infrastructure to create a functioning and thriving metropolis. The game will have a top-down view and will be played on a grid-based map.

## Gameplay:

Players will begin with a small plot of land and a set amount of resources. They must use these resources wisely to build their city and attract residents. Buildings such as homes, businesses, and municipal buildings will be available for purchase using the resources that players gather. Each building will have a specific function, such as providing housing for residents or generating income.

Players will need to manage their resources carefully, as building too quickly or recklessly can lead to bankruptcy. They must also keep their residents happy by providing necessary services such as schools, hospitals, and parks.

As players progress through the game, they will unlock new buildings and infrastructure, allowing them to create more complex and efficient cities. They will also face challenges such as natural disasters, crime, and traffic congestion, which will require them to adapt and make changes to their city.

## Art Style:
The game will have a simple and colorful art style, with cartoonish buildings and characters. The top-down view will allow players to see their entire city at once and easily manage its layout.

## Platforms:
The game will be available on PC and mobile platforms.

## Monetization:
The game will be free to download and play, with in-app purchases available for players who want to speed up their progress or unlock additional content. Ads may also be displayed to players who choose not to make in-app purchases.

## Conclusion:

Our simple city builder game offers players the opportunity to create and manage their own virtual city. With a variety of buildings, infrastructure, and challenges, players will have endless possibilities to explore and experiment with. The game's simple and colorful art style and availability on multiple platforms make it accessible to a wide audience.

## Requirements Alpha Version

List of Requirements for Alpha Version of Simple City Builder Game:

- [] Grid-based map with the ability to zoom in and out, scroll, and place buildings on it.
- [] A set of basic buildings (homes, businesses, and municipal buildings) for players to purchase and place on the map.
- [] A system for managing resources, such as money, materials, and population.
- [] A basic AI system for the residents, including their needs and preferences.
- [] A system for managing services, such as schools, hospitals, and parks.
- [] A set of challenges, such as natural disasters, crime, and traffic congestion.
- [] A simple user interface for managing the city, including buttons and menus for placing buildings, managing resources and services, and dealing with challenges.
- [] Basic graphics and animations for the buildings, characters, and environment.
- [] Basic sound effects and music to enhance the player's experience.
- [] Basic testing and debugging to ensure the game is stable and playable.

## Campaign

### Mission 1

The player has to place a house (empty) in a valid location and wait until an immigrant comming from the border of the map go in the direction of the house and occupies it, increasing the population (initially 0).

## Gradle

This project uses [Gradle](http://gradle.org/) to manage dependencies.
The Gradle wrapper was included, so you can run Gradle tasks using `gradlew.bat` or `./gradlew` commands.
Useful Gradle tasks and flags:

- `--continue`: when using this flag, errors will not stop the tasks from running.
- `--daemon`: thanks to this flag, Gradle daemon will be used to run chosen tasks.
- `--offline`: when using this flag, cached dependency archives will be used.
- `--refresh-dependencies`: this flag forces validation of all dependencies. Useful for snapshot versions.
- `build`: builds sources and archives of every project.
- `cleanEclipse`: removes Eclipse project data.
- `cleanIdea`: removes IntelliJ project data.
- `clean`: removes `build` folders, which store compiled classes and built archives.
- `eclipse`: generates Eclipse project data.
- `idea`: generates IntelliJ project data.
- `lwjgl3:jar`: builds application's runnable jar, which can be found at `lwjgl3/build/libs`.
- `lwjgl3:run`: starts the application.
- `test`: runs unit tests (if any).

Note that most tasks that are not specific to a single project can be run with `name:` prefix, where the `name` should be replaced with the ID of a specific project.
For example, `core:clean` removes `build` folder only from the `core` project.