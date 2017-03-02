package beans;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import data.MarvelCharacter;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.logging.Logger;

@SuppressWarnings("unused")
@Component
public class CharacterManager {
    private static final String URL_FIND_CHARACTER = "http://gateway.marvel.com/v1/public/characters?ts={ts}&apikey={apikey}&hash={hash}&nameStartsWith={searchString}";
    private static final String URL_GET_CHARACTER = "http://gateway.marvel.com/v1/public/characters/{character}?ts={ts}&apikey={apikey}&hash={hash}";
    private Logger logger = Logger.getLogger(CharacterManager.class.getName());
    private RestTemplate restTemplate = new RestTemplate();
    @Value("${publicKey}") private String publicKey;
    @Value("${privateKey}") private String privateKey;

    public String findCharacter(String characterString) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        logger.info(String.format("find character: '%s'", characterString));
        long timeStamp = Calendar.getInstance().getTimeInMillis();
        String hash = getHash(timeStamp);
        return restTemplate.getForObject(URL_FIND_CHARACTER, String.class, timeStamp, publicKey, hash, characterString);
    }

    public MarvelCharacter getCharacter(String characterId) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        logger.info(String.format("get character: '%s'", characterId));
        long timeStamp = Calendar.getInstance().getTimeInMillis();
        String hash = getHash(timeStamp);
        String characterAsString = restTemplate.getForObject(URL_GET_CHARACTER, String.class, characterId, timeStamp, publicKey, hash);
        JsonObject character = new JsonParser().parse(characterAsString).getAsJsonObject();
        JsonObject data = character.getAsJsonObject("data");
        JsonElement results = data.getAsJsonArray("results").get(0);
        return new Gson().fromJson(results.getAsJsonObject(), MarvelCharacter.class);
    }

    private String getHash(long timeStamp) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String stringToHash = timeStamp + privateKey + publicKey;
        return DigestUtils.md5Hex(stringToHash);
    }
}
