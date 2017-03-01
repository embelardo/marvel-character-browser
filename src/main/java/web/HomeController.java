package web;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.logging.Logger;

@SuppressWarnings("unused")
@RestController
public class HomeController {
    private static final String PUBLIC_KEY = "c1b04f647f9b8b84cbfe8098c505748c";
    private static final String PRIVATE_KEY = "9839199223925cba2ce7472d5f87866fa4ad6593";
    private RestTemplate restTemplate = new RestTemplate();
    private Logger logger = Logger.getLogger(HomeController.class.getName());

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String findCharacters(@RequestParam String searchString) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String url = "http://gateway.marvel.com/v1/public/characters?ts={ts}&apikey={apikey}&hash={hash}&nameStartsWith={searchString}";
        long ts = Calendar.getInstance().getTimeInMillis();
        String hash = getHash(ts);
        String msg = String.format("marvel character search string: '%s',  privateKey: %s, hash: %s", searchString, PRIVATE_KEY, hash);
        logger.info(msg);
        return restTemplate.getForObject(url, String.class, ts, PUBLIC_KEY, hash, searchString);
    }

    private static String getHash(long ts) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String rawString = ts + PRIVATE_KEY + PUBLIC_KEY;
        return DigestUtils.md5Hex(rawString);
    }
}
