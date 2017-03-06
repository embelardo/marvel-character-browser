package scratch;

import comics.character.Character;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class PopulateCharacterObject {
    static final String BLACK_WINDOW = "1009189";
    static final String CAPTAIN_AMERICA = "1009220";
    static final String FALCON = "1009297";
    static final String HAWKEYE = "1009338";
    static final String HULK = "1009351";
    static final String IRON_MAN = "1009368";
    static final String SPIDER_MAN = "1009610";
    static final String THOR = "1009664";

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        // parse json for character data
        System.out.println("parse json from marvel api");
        Character mc = ExtractDataFromJson.parseJson(CAPTAIN_AMERICA);
        // scrape html for character data
        System.out.println("scrape marvel html pages");
        ScrapeCharacterWikiPage.scrapeCharacterWikiHtml(mc);
        System.out.println(mc);
    }
}
