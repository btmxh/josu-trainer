# josu-trainer

## ~~ARCHIVED~~
~~Since FunOrange finally patched the Windows 7 bug, and I upgraded to Windows 10, this program is not needed anymore, so I'm gonna archive this repo.~~  
~~Some people actually use this, so I'm maintaining this program.~~

nvm use [cosutrainer](https://github.com/hwsmm/cosutrainer) instead, I'm too lazy lol.

## General

A Java rewrite of FunOrange's osu-trainer, with minimal dependencies

The reason that I made this is because osu-trainer crashes every time when I launched it on my system. I've checked the code and tried to rebuild the program in Visual Studio but I had some issues. I also want to try some osu! related programming.
(This is why if you have no problems with osu-trainer, there should be no reasons to use this app)

This also included a interop module for [gosu-memory](https://github.com/l3lackShark/gosumemory) to find the current playing map.

## Run

After that, download the lastest release from [here](https://github.com/ngoduyanh/josu-trainer/releases), and extract it. The GUI app should be in the bin folder, and named josu-trainer(.bat if on Windows)

You also need to run [gosu-memory](https://github.com/l3lackShark/gosumemory) for the program to read osu! memory data.

In order to speed up/slow down audio, you need [ffmpeg](https://www.ffmpeg.org). You can find Windows build [here](https://www.gyan.dev/ffmpeg/builds/). On Mac and Linux, you should use your package manager to install it. You should put ffmpeg executable into your PATH. If you don't want to do it, check the [Configurations](#configurations) section.

Note: You don't need Java to run the program.

## Configurations

You can configure the GUI app by placing a file named josutrainer-config.properties in the same directory as the executable. There are several configurations:

* ffmpeg - path to your ffmpeg executable (if you don't want to put it into PATH)
* gosu_memory_url - URL of outputted JSON from gosumemory
* update_interval - time in milliseconds between each update (an update is when the program read gosumemory's outputted JSON), setting it too low may make your CPU to parse too much JSON and cause you some top play chokes
* generate_empty_osz - if true, the program will generate a dummy osz file for you to force reload the mapset. This could be useful if you're running osu! by wine (these osz files are not empty, the name will probably change soon)
* osz_directory - the directory to generate "empty" osz files. Setting it to the Songs directory will make osu! auto-import the map (if it doesn't you need to press F5, it doesn't trigger a full reprocess).

(yeah I don't phrase it that well)

> If you run the program directly (using `gradlew :josu-trainer-gui:run`), you need to put the config file in the josu-trainer-gui directory.

Config file format:
```properties
ffmpeg=path/to/ffmpeg/executable
gosu_memory_url=http://localhost:727/json
generate_empty_osz=true
default_diff_name_format=${difficulty} (${rate}x) AR${ar} OD${od}
# etc...
```

Note: If you launch josu-trainer from another directory, the config file will be loaded from that directory.

## Default difficulty name format

To make this more convenient, you can provide josu-trainer with a default diff name format, like ```${difficulty} (${rate}x) AR${ar} OD${od} based ${artist}``` (this should be self-explanatory enough, it's based on some python string formatting module iirc). When provided with [this map](https://osu.ppy.sh/beatmapsets/1044333#osu/2183206), speed up 1.3x, AR10, OD10, the default diff name is ```Over the Dreams (1.3x) AR10 OD10 based KiRaRe```

This diff name will be synced, so for example you can adjust the AR and the diff name will automatically updated with your new AR value. But if you changed the diff name yourself, there will be no sync until you hit the reset button, or change the map.

These following tags (things to put between ```${``` and ```}``` are supported: ```title```, ```artist```, ```difficulty```, ```mapper```, ```ar```, ```hp```, ```od```, ```cs```, ```rate``` (speed-up/slow-down rate), ```bpm```. Except for ```rate```, it should be self-explanatory.

## Dependencies

* [jackson](https://github.com/FasterXML/jackson-databind) (licensed under Apache License 2.0) to parse JSON
* [junit5](https://github.com/junit-team/junit5) (licensed under Eclipse Public License) for unit tests
* [openjfx](https://github.com/openjdk/jfx) (licensed under GPL v2 with the Classpath exception) for GUI
* [ffmpeg](https://www.ffmpeg.org) (licensed under LGPL v2.1) to speed up/slow down audio (via the command line)
* [controlsfx](https://github.com/controlsfx/controlsfx) (licensed under BSD) for better JavaFX GUI

This project, although not directly, also depends on [gosu-memory](https://github.com/l3lackShark/gosumemory) to get current osu! memory data.

I also got modena-dark.css from https://github.com/joffrey-bion/javafx-themes/blob/master/css/modena_dark.css

## Build

In order to build executables, you need JDK 11+ (for modules to work, maybe I'll add Java 8 support in the future). Just run this in the terminal: 

```bash
gradlew :josu-trainer-gui:jlink
```

The executable will be called josu-trainer (on Mac or Linux) or josu-trainer.bat (on Windows) in ./build/image/bin

Note: You just need Java to build the program, the release already included a small JRE built by jlink.

## Run

If you just want to run the program, just do ```gradlew :josu-trainer-gui:run```

## Copyright

I guess I'm stealing FunOrange's original work, so if you (FunOrange) wants me to delete this repo, you can DM me on Twitter (@majotb420) to make this repo private.

