package scratch;

import com.google.gson.*;
import marvel.Character;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Properties;

public class ExtractDataFromJson {
    private static Properties props;
    private static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        Character c = parseJson(PopulateCharacterObject.SPIDER_MAN);
        System.out.println(c);
    }

    public static Character parseJson(String characterId) throws IOException, NoSuchAlgorithmException {
        initProps();
        String url = "http://gateway.marvel.com/v1/public/characters/{character}?ts={ts}&apikey={apikey}&hash={hash}";
        long ts = Calendar.getInstance().getTimeInMillis();
        String hash = getHash(ts);
        String characterAsString = restTemplate.getForObject(url, String.class, characterId, ts, props.getProperty("key.public"), hash);
        JsonObject character = new JsonParser().parse(characterAsString).getAsJsonObject();
        JsonObject data = character.getAsJsonObject("data");
        JsonElement results = data.getAsJsonArray("results").get(0);
        Character c = new Gson().fromJson(results.getAsJsonObject(), Character.class);
        return c;
    }

    private static void initProps() throws IOException {
        props = new Properties();
        FileInputStream fis = new FileInputStream("apikeys.properties");
        props.load(fis);
    }

    private static String getHash(long ts) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String rawString = ts + props.getProperty("key.private") + props.getProperty("key.public");
        return DigestUtils.md5Hex(rawString);
    }

    @SuppressWarnings("unused")
    private static void prettyPrint(String jsonAsString) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement json = gson.fromJson(jsonAsString, JsonElement.class);
        System.out.println(gson.toJson(json));
    }
}
