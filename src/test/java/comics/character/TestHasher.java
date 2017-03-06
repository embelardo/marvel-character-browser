package comics.character;

/**
 * A {@link Hasher} object that returns canned responses.
 */
public class TestHasher implements Hasher {
    @Override
    public long getTimeStamp() {
        return 1488814980904L;
    }

    @Override
    public String getHash(long timeStamp) {
        return "2186f83a7c7fa7dcd32c17c54eacc62e";
    }
}
