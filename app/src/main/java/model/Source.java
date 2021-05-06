package model;

import org.json.JSONObject;

import java.io.Serializable;

public class Source implements Serializable {

    public String id;
    public String name;

    public static Source parseSource(JSONObject jsonObject){
        Source source = new Source();

        source.id = jsonObject.optString("id");
        source.name = jsonObject.optString("name");

        return source;
    }

}
