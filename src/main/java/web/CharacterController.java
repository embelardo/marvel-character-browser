package web;

import application.CharacterManager;
import data.MarvelCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@SuppressWarnings("unused")
@Controller
public class CharacterController {
    @Autowired
    private CharacterManager characterManager;

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String findCharacter(@RequestParam String searchString, Model model) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        List<MarvelCharacter> searchResultList = characterManager.findCharacter(searchString);
        model.addAttribute("searchResultList", searchResultList);
        return "searchResultList";
    }

    @ResponseBody
    @RequestMapping(value = "/findjson", method = RequestMethod.GET)
    public List<MarvelCharacter> findCharacterJson(@RequestParam String searchString, Model model) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return characterManager.findCharacter(searchString);
    }

    @RequestMapping(value = "/character/{characterId}", method = RequestMethod.GET)
    public String getCharacter(@PathVariable String characterId, Model model) throws IOException, NoSuchAlgorithmException {
        MarvelCharacter mc = characterManager.getCharacter(characterId);
        model.addAttribute("marvelCharacter", mc);
        return "characterDetail";
    }

    @ResponseBody
    @RequestMapping(value = "/characterjson/{characterId}", method = RequestMethod.GET)
    public MarvelCharacter getCharacterJson(@PathVariable String characterId, Model model) throws IOException, NoSuchAlgorithmException {
        return characterManager.getCharacter(characterId);
    }
}
