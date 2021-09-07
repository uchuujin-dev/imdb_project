import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.commons.lang3.ClassUtils;
import org.json.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.csv.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.apache.commons.lang3.ClassUtils.getSimpleName;

public class Main {
    public static void main(String[] args) {


        JSONObject moviesJson = new JSONObject(ReadImdbAsJson.getMovies());
        JSONObject moviesJsonOutcast = new JSONObject();
        System.out.println("movies length (before removal) : " +moviesJson.length());

        // remove unnecessary keys
        for(int i=0; i<moviesJson.length(); i++){

            moviesJson.getJSONObject(String.valueOf(i)).remove("actor1");
            moviesJson.getJSONObject(String.valueOf(i)).remove("actor2");
            moviesJson.getJSONObject(String.valueOf(i)).remove("actor3");
            moviesJson.getJSONObject(String.valueOf(i)).remove("genresUnsplit");
        }


// remove null items
        JSONArray keys = moviesJson.getJSONObject("4").names();

        int movieLength = moviesJson.length();

            for(int i=0; i< movieLength; i++){

            for (int j = 0; j < keys.length(); j++) {
                if (
                        moviesJson.getJSONObject(String.valueOf(i)).get(keys.getString(j)) == JSONObject.NULL || moviesJson.getJSONObject(String.valueOf(i)).get(keys.getString(j)) == "" ||
                moviesJson.getJSONObject(String.valueOf(i)).get("gross") == Integer.valueOf(0) ||
                        moviesJson.getJSONObject(String.valueOf(i)).get("duration") == Integer.valueOf(0)||
                        moviesJson.getJSONObject(String.valueOf(i)).get("score") == Integer.valueOf(0)||
                        moviesJson.getJSONObject(String.valueOf(i)).get("year") == Integer.valueOf(0)||
                        moviesJson.getJSONObject(String.valueOf(i)).get("budget") == Integer.valueOf(0)
                ){

                    moviesJsonOutcast.append(String.valueOf(i), moviesJson.getJSONObject(String.valueOf(i)));
                    moviesJson.remove(String.valueOf(i));

                    break;
                }
            }

        }
        try {
            FileWriter file = new FileWriter("src/main/resources/log.json");
            file.write(String.valueOf(moviesJson));
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



        System.out.println("movies length : " +moviesJson.length());
        System.out.println("movies with empty fields: " + moviesJsonOutcast.length());







        }

}
