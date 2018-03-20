package com.example.martinb.newsapp.data.remote;

import com.example.martinb.newsapp.model.Source;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by martinb on 2/21/2018.
 */

public class SourceDes implements JsonDeserializer<Source> {

    @Override
    public Source deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Source source = new Source();
        JsonObject repoJsonObject = json.getAsJsonObject();
        source.setId(repoJsonObject.get("id").getAsString());
        source.setName(repoJsonObject.get("name").getAsString());
        return source;
    }
}

