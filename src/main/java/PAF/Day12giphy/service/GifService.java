// package PAF.Day12giphy.service;

// import java.io.StringReader;
// import java.util.LinkedList;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.http.RequestEntity;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Service;
// import org.springframework.web.client.RestTemplate;
// import org.springframework.web.util.UriComponentsBuilder;

// import jakarta.json.Json;
// import jakarta.json.JsonArray;
// import jakarta.json.JsonObject;
// import jakarta.json.JsonReader;

// @Service
// public class GifService {
    
//     //GIPHY_API_KEY
//     @Value("${giphy.api.key}")
//     private String giphyKey;

//     private static final String URL_BASE = "https://api.giphy.com/v1/gifs/search";

//     public List<String> createGiphy (String q) {
//         return createGiphy (q, 10, "g");
//     }

//     public List<String> createGiphy (String q, Integer limit) {
//         return createGiphy (q, limit, "g");
//     }

//     public List<String> createGiphy (String q, String rating) {
//         return createGiphy (q, 10, rating);
//     }

//     public List<String> createGiphy (String q, Integer limit, String rating) {

//         List<String> result = new LinkedList<>();

//         String newGiphy = UriComponentsBuilder.fromUriString(URL_BASE)
//                 .queryParam("api_key", giphyKey)
//                 .queryParam("q", q)
//                 .queryParam("limit", limit)
//                 .queryParam("rating", rating)
//                 .toUriString();

//         RequestEntity <Void> request = RequestEntity.get(newGiphy)
//                 .build();

//         RestTemplate template = new RestTemplate();
//         ResponseEntity <String> response = null;
        
//         try {
//             response = template.exchange(request, String.class);
//         } catch (Exception ex) {
//             ex.printStackTrace();
//             return result;
//         }

//         JsonReader reader = Json.createReader(new StringReader(response.getBody()));
//         JsonObject gifs = reader.readObject();
//         JsonArray data = gifs.getJsonArray("data");
//         for (int i = 0; i<data.size(); i++) {
//             JsonObject gif = data.getJsonObject(i);
//             String image = gif.getJsonObject("images")
//                     .getJsonObject("fixed_width")
//                     .getString("url");
//             result.add(image);
//         }
//         return result;
//     }
    
// }
