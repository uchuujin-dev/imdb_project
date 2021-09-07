import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadImdbAsJson {

    public static String getMovies() {
        String fileName = ConfigManager.imdbFileLocation();

        List<ImdbDto> movies = new ArrayList<>();
        List<ImdbDto> beans = null;
        try {
            beans = new CsvToBeanBuilder(new FileReader(fileName))
                    .withType(ImdbDto.class)
                    .build()
                    .parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        beans.forEach(m -> {
            if (m != null) {
                movies.add(m);
            }
        });

        Map<Integer, ImdbDto> moviesMap = new HashMap<>();
        for (int i = 0; i < movies.size(); i++) {
            moviesMap.put(i, movies.get(i));
            movies.get(i).setId(i);
        }

        ObjectMapper mapper = new ObjectMapper();
        String json = new String();
        try {
            json =
                    mapper.writeValueAsString(moviesMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static String cleanImdbJson(){
        String json = "";
        return json;
    }

}
