package nycuro.utils;

import com.google.gson.Gson;

public class JSONParser<T> {

    private final Class<T> type;

    public JSONParser(Class<T> type) {
        this.type = type;
    }

    public Class<T> getType() {
        return this.type;
    }

    public T parse(String url) {
        Gson gson = new Gson();
        T jsonObject = gson.fromJson(url, this.type);
        return jsonObject;
    }
}


