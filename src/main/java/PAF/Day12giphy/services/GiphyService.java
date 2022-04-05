package PAF.Day12giphy.services;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class GiphyService {

    //GIPHY_API_KEY
    @Value("${giphy.api.key}")
    private String giphyKey;

    private static final String GIPHY_SEARCH = "https://api.giphy.com/v1/gifs/search";

    //this is to print out the api_key
    @PostConstruct
    public void init() {
        System.out.printf("api key: %s\n", giphyKey);
    }

    //Creating three overloaded methods below
    public List<String> getGiphs(String q){
        return getGiphs(q, "pg", 10);
    }
    
    public List<String> getGiphs(String q, String rating){
        return getGiphs(q, rating, 10);
    }

    public List<String> getGiphs(String q, Integer limit){
        return getGiphs(q, "pg", limit);
    }
    
    public List<String> getGiphs(String q, String rating, Integer limit){
        List<String> result = new LinkedList<>();

        String newGiphy = UriComponentsBuilder.fromUriString(GIPHY_SEARCH)
                .queryParam("api_key", giphyKey)
                .queryParam("q", q)
                .queryParam("rating", rating)
                .queryParam("limit", limit)
                .toUriString();

        RequestEntity<Void> request = RequestEntity.get(newGiphy)
                .build();

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response = null; 
        
        try{
            response = template.exchange(request, String.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return result;
        }

        //image fixed_width.irl
        JsonReader reader = Json.createReader(new StringReader(response.getBody()));
        JsonObject gifs = reader.readObject();
        JsonArray data = gifs.getJsonArray("data");
        for (int i=0; i<data.size(); i++) {
            JsonObject gif = data.getJsonObject(i);
            String image = gif.getJsonObject("images").getJsonObject("fixed_width").getString("url");
            result.add(image);
        }
        // InputStream is = new ByteArrayInputStream(response.getBody().getBtyes()) {
        //     JsonReader reader = Json.createReader(is);
        //     JsonObject obj = reader.readObject();
            
        //     JsonArray dataBody = obj.getJsonArray("data");
        //     for (int i=0; i<dataBody.size(); i++) {
        //         JsonObject jsonObj = dataBody(i);

        //     }
        // }
        return result;
    }

    
}
