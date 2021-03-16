package com.dah.gmi;

import com.dah.gmi.data.GosuMemData;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class GosuMemoryLoader {
    public URL jsonURL;
    public ObjectMapper mapper;

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

    public GosuMemData load() throws IOException {
        return mapper.readValue(jsonURL, GosuMemData.class);
    }
}
