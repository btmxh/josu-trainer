# josu-trainer
A Java rewrite of FunOrange's osu-trainer, with minimal dependencies

The reason that I made this is because osu-trainer crashes every time when I launched it on my system. I've checked the code and tried to rebuild the program in Visual Studio but I had some issues. I also want to try some osu! related programming.
(This is why if you have no problems with osu-trainer, there should be no reasons to use this app)

This also included a interop module for [gosu-memory](https://github.com/l3lackShark/gosumemory) to find the current playing map.

## Run

After that, download the lastest release from [here](https://github.com/ngoduyanh/josu-trainer/releases), and extract it. The GUI app should be in the bin folder, and named josu-trainer(.bat if on Windows)

You also need to run [gosu-memory](https://github.com/l3lackShark/gosumemory) for the program to read osu! memory data.

In order to speed up/slow down audio, you need [ffmpeg](https://www.ffmpeg.org). You can find Windows build [here](https://www.gyan.dev/ffmpeg/builds/). On Mac and Linux, you should use your package manager to install it. You should put ffmpeg executable into your PATH. If you don't want to do it, check the [Configurations](#configurations-not-tested) section.

Note: You don't need Java to run the program.

## Configurations (NOT TESTED)

You can configure the GUI app by placing a file named josutrainer-config.properties in the same directory as the executable. There are several configurations:

* ffmpeg - path to your ffmpeg executable (if you don't want to put it into PATH)
* gosu_memory_url - URL of outputted JSON from gosumemory
* update_interval - time in milliseconds between each update (an update is when the program read gosumemory's outputted JSON), setting it too low may make your CPU to parse too much JSON and cause you some top play chokes

(yeah I don't phrase it that well)

Config file format:
```properties
ffmpeg=path/to/ffmpeg/executable
gosu_memory_url=http://localhost:727/json
# etc...
```

## Dependencies

* [jackson](https://github.com/FasterXML/jackson-databind) (licensed under Apache License 2.0) to parse JSON
* [junit5](https://github.com/junit-team/junit5) (licensed under Eclipse Public License) for unit tests
* [openjfx](https://github.com/openjdk/jfx) (licensed under GPL v2 with the Classpath exception) for GUI
* [ffmpeg](https://www.ffmpeg.org) (licensed under LGPL v2.1) to speed up/slow down audio (via the command line)

This project, although not directly, also depends on [gosu-memory](https://github.com/l3lackShark/gosumemory) to get current osu! memory data.

## Build

In order to build executables, you need JDK 11+ (for modules to work, maybe I'll add Java 8 support in the future). Just run this in the terminal: 

```bash
gradlew :josu-trainer-gui:jlink
```

The executable will be called josu-trainer (on Mac or Linux) or josu-trainer.bat (on Windows) in ./build/image/bin

Note: You just need Java to build the program, the release already included a small JRE built by jlink.

## Copyright

I guess I'm stealing FunOrange's original work, so if you (FunOrange) wants me to delete this repo, you can DM me on Twitter (@majotb420) to make this repo private.

Also, this repo will be licensed under UNLICENSE (no one will use this code tbh).

