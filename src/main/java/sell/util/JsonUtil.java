package sell.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.GsonBuildConfig;

public class JsonUtil {

    public static String toJson(Object object){
        GsonBuilder gsonBuild=new GsonBuilder();
        gsonBuild.setPrettyPrinting();
        Gson gson=gsonBuild.create();
        return gson.toJson(object);

    }
}
