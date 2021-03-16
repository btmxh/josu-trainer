package com.dah.josutrainer.core;

import com.dah.gmi.GosuMemoryLoader;
import com.dah.gmi.data.GosuMemData;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class SpeedUpTest {
    @Test
    public void testCurrentMap() throws IOException {
        GosuMemoryLoader loader = new GosuMemoryLoader();
        GosuMemData gosuData = loader.load();
        Beatmap map = new Beatmap(gosuData);
        map.setAR(10.0);
        map.setCS(3.5);
        map.speedUp(1.8);
        map.save(map.get("Metadata", "Version") + " x 1.8");
    }
}
