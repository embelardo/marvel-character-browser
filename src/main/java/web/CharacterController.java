package web;

import beans.CharacterManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@SuppressWarnings("unused")
@RestController
public class CharacterController {
    @Autowired
    private CharacterManager characterManager;

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String findCharacters(@RequestParam String searchString) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return characterManager.findCharacters(searchString);
    }
}
