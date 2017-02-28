package scratch;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Properties;

public class BuildCharacterGetRequest {
    private static Properties props;

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        initProps();
        String searchString = "loki";
        System.out.println("marvel character search string: '" + searchString + "'");
        String url = "http://gateway.marvel.com/v1/public/characters?ts=%d&apikey=%s&hash=%s&nameStartsWith=%s";
        long ts = Calendar.getInstance().getTimeInMillis();
        String hash = getHash(ts);
        System.out.println("privateKey: " + props.getProperty("key.private") + ", hash: " + hash);
        System.out.println(String.format(url, ts, props.getProperty("key.public"), hash, searchString));
    }

    private static void initProps() throws IOException {
        props = new Properties();
        FileInputStream fis = new FileInputStream("apikeys.properties");
        props.load(fis);
    }

    private static String getHash(long ts) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String stringToHash = ts + props.getProperty("key.private") + props.getProperty("key.public");
        String hash = DigestUtils.md5Hex(stringToHash);
        //        System.out.println("original: " + stringToHash + ", hash: " + hash);
        return hash;
    }
}
