package comics.character;

public interface Hasher {
    long getTimeStamp();
    String getHash(long timeStamp);
}
