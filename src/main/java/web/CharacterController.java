package web;

import beans.CharacterManager;
import data.MarvelCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@SuppressWarnings("unused")
@RestController
public class CharacterController {
    @Autowired
    private CharacterManager characterManager;

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String findCharacter(@RequestParam String searchString) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return characterManager.findCharacter(searchString);
    }

    @RequestMapping(value = "/character/{characterId}", method = RequestMethod.GET)
    public MarvelCharacter getCharacter(@PathVariable String characterId) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return characterManager.getCharacter(characterId);
    }
}
