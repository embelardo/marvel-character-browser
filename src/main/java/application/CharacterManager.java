package application;

import com.google.gson.*;
import data.MarvelCharacter;
import org.apache.commons.codec.digest.DigestUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

@SuppressWarnings("unused")
@Component
public class CharacterManager {
    private Logger logger = Logger.getLogger(CharacterManager.class.getName());
    private RestTemplate restTemplate = new RestTemplate();
    @Value("${urlFindCharacter}") private String urlFindCharacter;
    @Value("${urlGetCharacter}") private String urlGetCharacter;
    @Value("${publicKey}") private String publicKey;
    @Value("${privateKey}") private String privateKey;

    public List<MarvelCharacter> findCharacter(String characterString) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        logger.info(String.format("find character: '%s'", characterString));
        long timeStamp = Calendar.getInstance().getTimeInMillis();
        String hash = getHash(timeStamp);
        String charactersAsString = restTemplate.getForObject(urlFindCharacter, String.class, timeStamp, publicKey, hash, characterString);
        JsonObject characters = new JsonParser().parse(charactersAsString).getAsJsonObject();
        JsonObject data = characters.getAsJsonObject("data");
        JsonArray results = data.getAsJsonArray("results");
        Gson gson = new Gson();
        List<MarvelCharacter> characterResults = new ArrayList<>();
        for (JsonElement e : results) {
            characterResults.add(gson.fromJson(e, MarvelCharacter.class));
        }
        return characterResults;
    }

    public MarvelCharacter getCharacter(String characterId) throws IOException, NoSuchAlgorithmException {
        logger.info(String.format("get character: '%s'", characterId));
        MarvelCharacter mc = parseCharacterJson(characterId);
        scrapeCharacterWikiHtml(mc);
        logger.info(mc.toString());
        return mc;
    }

    public MarvelCharacter parseCharacterJson(String characterId) throws IOException, NoSuchAlgorithmException {
        long timeStamp = Calendar.getInstance().getTimeInMillis();
        String hash = getHash(timeStamp);
        String characterAsString = restTemplate.getForObject(urlGetCharacter, String.class, characterId, timeStamp, publicKey, hash);
        JsonObject character = new JsonParser().parse(characterAsString).getAsJsonObject();
        JsonObject data = character.getAsJsonObject("data");
        JsonElement results = data.getAsJsonArray("results").get(0);
        return new Gson().fromJson(results.getAsJsonObject(), MarvelCharacter.class);
    }

    @SuppressWarnings("Duplicates")
    public void scrapeCharacterWikiHtml(MarvelCharacter mc) throws IOException {
        String characterUrl = mc.getWikiUrl();
        if (characterUrl == null) {
            return;
        }

        Document doc = Jsoup.connect(characterUrl).get();

        // information
        Element realName = doc.select("p:contains(real name)").first();
        mc.setRealName(realName == null ? "None" : realName.ownText());

        Element aliases = doc.select("p:contains(aliases)").first();
        mc.setAliases(aliases == null ? "None" : aliases.ownText());

        Element identity = doc.select("p:contains(identity)").first();
        mc.setIdentity(identity == null ? "None" : identity.ownText());

        Element citizenship = doc.select("p:contains(citizenship)").first();
        mc.setCitizenship(citizenship == null ? "None" : citizenship.ownText());

        Element placeOfBirth = doc.select("p:contains(place of birth)").first();
        mc.setPlaceOfBirth(placeOfBirth == null ? "None" : placeOfBirth.ownText());

        Element firstAppearance = doc.select("p:contains(first appearance)").first();
        mc.setFirstAppearance(firstAppearance == null ? "None" : firstAppearance.ownText());

        Element origin = doc.select("p:contains(origin)").first();
        mc.setOrigin(origin == null ? "None" : origin.ownText());

        Element occupation = doc.select("div#char-occupation-content").first();
        mc.setOccupation(occupation == null ? "None" : occupation.ownText());

        Element relatives = doc.select("div#char-relatives-content").first();
        mc.setRelatives(relatives == null ? "None" : relatives.text());

        Element groupAffiliation = doc.select("div#char-affiliation-content").first();
        mc.setGroupAffiliation(groupAffiliation == null ? "None" : groupAffiliation.text());

        Element education = doc.select("div#char-education-content").first();
        mc.setEducation(education == null ? "None" : education.ownText());

        Element physicalAttributes = doc.select("div#char-physicals-content").first();
        mc.setPhysicalAttributes(physicalAttributes == null ? "None" : physicalAttributes.text());

        Element powers = doc.select("div#char-powers-content").first();
        mc.setPowers(powers == null ? "None" : powers.text());

        Element paraphernalia = doc.select("div#char-paraphernalia-content").first();
        mc.setParaphernalia(paraphernalia == null ? "None" : paraphernalia.text());

        // power ratings
        Elements attrs = doc.select("div[class=p-right ratingvalue]");
        for (Element attr : attrs) {
            String power = attr.previousElementSibling().child(0).text();
            String rating = attr.attr("style");
            switch (rating) {
                case "width:146px;":
                    rating = "7 / 7";
                    break;
                case "width:126px;":
                    rating = "6 / 7";
                    break;
                case "width:105px;":
                    rating = "5 / 7";
                    break;
                case "width:84px;":
                    rating = "4 / 7";
                    break;
                case "width:63px;":
                    rating = "3 / 7";
                    break;
                case "width:42px;":
                    rating = "2 / 7";
                    break;
                case "width:21px;":
                    rating = "1 / 7";
            }

            mc.getPowerRatings().put(power, rating);
        }
    }

    private String getHash(long timeStamp) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String stringToHash = timeStamp + privateKey + publicKey;
        return DigestUtils.md5Hex(stringToHash);
    }
}
