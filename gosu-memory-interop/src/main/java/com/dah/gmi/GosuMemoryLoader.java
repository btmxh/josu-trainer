package com.dah.gmi;

import com.dah.gmi.data.GosuMemData;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class GosuMemoryLoader {
    /**
     * URL of the json file outputed by gosu-memory. The default value that gosu-memory specified is <a href="http://localhost:24050/json">http://localhost:24050/json</a>
     */
    public URL jsonURL;
    /**
     * Jackson data-binding's ObjectMapper to parse the json data into our data structure
     */
    public ObjectMapper mapper;

    /**
     * Create a gosu-memory data loader
     */
    public GosuMemoryLoader() {
        try {
            jsonURL = new URL("http://localhost:24050/json");   //default json URL
        } catch (MalformedURLException e) {
            throw new InternalError(e);
        }
        mapper = new ObjectMapper();

        //Make sure that ObjectMapper can't fail.
        //gosu-memory's json file have a lot of info that this program don't care about,
        //so it would be dumb to fail loading current map because of an error while loading tourney client data, for example
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY, false);
        mapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
    }

    /**
     * Load current osu! memory data from <code>jsonURL</code>
     * @return the parsed data
     * @throws IOException if an I/O error occurred, most likely that <code>jsonURL</code> is set to a different value, or gosu-memory is not running
     */
    public GosuMemData load() throws IOException {
        return mapper.readValue(jsonURL, GosuMemData.class);
    }
}
