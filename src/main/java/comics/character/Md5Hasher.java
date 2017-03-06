package comics.character;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;

import java.util.Calendar;

/**
 * Computes md5 hashes for REST requests to the Marvel REST API.
 */
public class Md5Hasher implements Hasher {
    @Value("${publicKey}") private String publicKey;
    @Value("${privateKey}") private String privateKey;

    /**
     * Returns the current timestamp.
     * @return timestamp in milliseconds
     */
    @Override
    public long getTimeStamp() {
        return Calendar.getInstance().getTimeInMillis();
    }

    /**
     * Computes the md5 hash from timestamp, private key, and public key.
     * @param timeStamp the current timestamp
     * @return md5 hash value
     */
    @Override
    public String getHash(long timeStamp) {
        String stringToHash = timeStamp + privateKey + publicKey;
        return DigestUtils.md5Hex(stringToHash);
    }
}
