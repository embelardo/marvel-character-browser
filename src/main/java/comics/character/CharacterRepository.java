package comics.character;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.*;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@SuppressWarnings("unused")
public interface CharacterRepository {
    List<Character> findCharacter(String characterString) throws UnsupportedEncodingException, NoSuchAlgorithmException;
    Character getCharacter(String characterId) throws IOException, NoSuchAlgorithmException;
}
