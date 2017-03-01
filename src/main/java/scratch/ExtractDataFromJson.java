package scratch;

import com.google.gson.*;
import data.MarvelCharacter;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Properties;

public class ExtractDataFromJson {
    private static Properties apiKeys;
    private static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        MarvelCharacter c = parseJson(PopulateCharacterObject.SPIDER_MAN);
        System.out.println(c);
    }

    public static MarvelCharacter parseJson(String characterId) throws IOException, NoSuchAlgorithmException {
        initProps();
        String url = "http://gateway.marvel.com/v1/public/characters/{character}?ts={ts}&apikey={apikey}&hash={hash}";
        long ts = Calendar.getInstance().getTimeInMillis();
        String hash = getHash(ts);
        String characterAsString = restTemplate.getForObject(url, String.class, characterId, ts, apiKeys.getProperty("publicKey"), hash);
        JsonObject character = new JsonParser().parse(characterAsString).getAsJsonObject();
        JsonObject data = character.getAsJsonObject("data");
        JsonElement results = data.getAsJsonArray("results").get(0);
        MarvelCharacter mc = new Gson().fromJson(results.getAsJsonObject(), MarvelCharacter.class);
        return mc;
    }

    private static void initProps() throws IOException {
        apiKeys = new Properties();
        FileInputStream fis = new FileInputStream("apikeys.properties");
        apiKeys.load(fis);
    }

    private static String getHash(long ts) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String rawString = ts + apiKeys.getProperty("privateKey") + apiKeys.getProperty("publicKey");
        return DigestUtils.md5Hex(rawString);
    }

    @SuppressWarnings("unused")
    private static void prettyPrint(String jsonAsString) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement json = gson.fromJson(jsonAsString, JsonElement.class);
        System.out.println(gson.toJson(json));
    }
}
