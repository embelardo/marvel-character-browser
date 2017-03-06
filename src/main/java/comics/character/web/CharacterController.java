package comics.character.web;

import comics.character.Character;
import comics.character.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Controller for all Marvel character-oriented endpoints.
 */
@SuppressWarnings("unused")
@Controller
public class CharacterController {
    @Autowired
    private CharacterRepository characterManager;

    /**
     * This web application's home page.
     * @return the view to render the home page
     */
    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    /**
     * Search for a Marvel character using a "starts with" search key.
     * @param searchString the search key
     * @param model the model returned by this controller
     * @return the view to render the search results
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String findCharacter(@RequestParam String searchString, Model model) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        List<Character> searchResultList = characterManager.findCharacter(searchString);
        model.addAttribute("searchResultList", searchResultList);
        return "searchResultList";
    }

    /**
     * Search for a Marvel character using a "starts with" search key.
     * @param searchString
     * @param model the model returned by this controller
     * @return the JSON response of the Marvel REST API
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @ResponseBody
    @RequestMapping(value = "/findjson", method = RequestMethod.GET)
    public List<Character> findCharacterJson(@RequestParam String searchString, Model model) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return characterManager.findCharacter(searchString);
    }

    /**
     * Get the details of a specific Marvel character.
     * @param characterId the character's unique identifier in the Marvel REST API
     * @param model the model returned by this controller
     * @return the view to render the character details
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(value = "/character/{characterId}", method = RequestMethod.GET)
    public String getCharacter(@PathVariable String characterId, Model model) throws IOException, NoSuchAlgorithmException {
        Character mc = characterManager.getCharacter(characterId);
        model.addAttribute("marvelCharacter", mc);
        return "characterDetail";
    }

    /**
     * Get the details of a specific Marvel character.
     * @param characterId the character's unique identifier in the Marvel REST API
     * @param model the model returned by this controller
     * @return the JSON response of the Marvel REST API
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @ResponseBody
    @RequestMapping(value = "/characterjson/{characterId}", method = RequestMethod.GET)
    public Character getCharacterJson(@PathVariable String characterId, Model model) throws IOException, NoSuchAlgorithmException {
        return characterManager.getCharacter(characterId);
    }
}
