package beans;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Logger;

@SuppressWarnings("unused")
@Component
public class CharacterManager {
    private static final String URL_CHARACTERS = "http://gateway.marvel.com/v1/public/characters?ts={ts}&apikey={apikey}&hash={hash}&nameStartsWith={searchString}";
    private Logger logger = Logger.getLogger(CharacterManager.class.getName());
    private RestTemplate restTemplate = new RestTemplate();
    private Properties apiKeys;
    private String publicKey;
    private String privateKey;

    public CharacterManager() throws IOException {
        apiKeys = initProps();
        publicKey = apiKeys.getProperty("publicKey");
        privateKey = apiKeys.getProperty("privateKey");
    }

    public String findCharacters(String searchString) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        long timeStamp = Calendar.getInstance().getTimeInMillis();
        String hash = getHash(timeStamp);
        logger.info(String.format("search string: '%s', privateKey: %s, hash: %s", searchString, privateKey, hash));
        return restTemplate.getForObject(URL_CHARACTERS, String.class, timeStamp, publicKey, hash, searchString);
    }

    private String getHash(long timeStamp) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String stringToHash = timeStamp + privateKey + publicKey;
        return DigestUtils.md5Hex(stringToHash);
    }

    private Properties initProps() throws IOException {
        Properties p= new Properties();
        p.load(getClass().getClassLoader().getResourceAsStream("apikeys.properties"));
        return p;
    }
}
