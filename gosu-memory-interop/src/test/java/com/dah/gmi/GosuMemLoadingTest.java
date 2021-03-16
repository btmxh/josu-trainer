package com.dah.gmi;

import com.dah.gmi.data.GosuMemData;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GosuMemLoadingTest {
    @Test
    public void test() throws IOException {
        GosuMemoryLoader loader = new GosuMemoryLoader();
        loader.jsonURL = GosuMemLoadingTest.class.getResource("/test.json");

        GosuMemData data = loader.load();

        assertEquals(data.getMenu().getBm().getMetadata().getArtist(), "Kano");
        assertEquals(data.getGameplay().getAccuracy(), 100.0);
    }
}
