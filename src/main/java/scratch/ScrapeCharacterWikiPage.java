package scratch;

import data.MarvelCharacter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ScrapeCharacterWikiPage {
    public static void main(String[] args) throws IOException {}

    public static void scrapeCharacterWikiHtml(MarvelCharacter mc) throws IOException {
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
            mc.getPowerRatings().put(power, rating);
        }
    }

    public static void scrapeHtmlAndPrint(String characterUrl) throws IOException {
        Document doc = Jsoup.connect(characterUrl).get();

        // information
        Element realName = doc.select("p:contains(real name)").first();
        System.out.println("real name\n -- " + (realName == null ? "None" : realName.ownText()));

        Element aliases = doc.select("p:contains(aliases)").first();
        System.out.println("aliases\n -- " + (aliases == null ? "None" : aliases.ownText()));

        Element identity = doc.select("p:contains(identity)").first();
        System.out.println("identity\n -- " + (identity == null ? "None" : identity.ownText()));

        Element citizenship = doc.select("p:contains(citizenship)").first();

        System.out.println("citizenship\n -- " + (citizenship == null ? "None" : citizenship.ownText()));

        Element placeOfBirth = doc.select("p:contains(place of birth)").first();
        System.out.println("place of birth\n -- " + (placeOfBirth == null ? "None" : placeOfBirth.ownText()));

        Element firstAppearance = doc.select("p:contains(first appearance)").first();
        System.out.println("first appearance\n -- " + (firstAppearance == null ? "None" : firstAppearance.ownText()));

        Element origin = doc.select("p:contains(origin)").first();
        System.out.println("origin\n -- " + (origin == null ? "None" : origin.ownText()));

        Element occupation = doc.select("div#char-occupation-content").first();
        System.out.println("occupation\n -- " + (occupation == null ? "None" : occupation.ownText()));

        Element relatives = doc.select("div#char-relatives-content").first();
        System.out.println("relatives\n -- " + (relatives == null ? "None" : relatives.text()));

        Element groupAffiliation = doc.select("div#char-affiliation-content").first();
        System.out.println("group affiliation\n -- " + (groupAffiliation == null ? "None" : groupAffiliation.text()));

        Element education = doc.select("div#char-education-content").first();
        System.out.println("education\n -- " + (education == null ? "None" : education.ownText()));

        Element physicalAttributes = doc.select("div#char-physicals-content").first();
        System.out.println("physical attributes\n -- " + (physicalAttributes == null ? "None" : physicalAttributes.text()));

        Element powers = doc.select("div#char-powers-content").first();
        System.out.println("powers\n -- " + (powers == null ? "None" : powers.text()));

        Element paraphernalia = doc.select("div#char-paraphernalia-content").first();
        System.out.println("paraphernalia\n -- " + (paraphernalia == null ? "None" : paraphernalia.text()));

        System.out.println();

        // power ratings
        Elements attrs = doc.select("div[class=p-right ratingvalue]");
        for (Element attr : attrs) {
            // get attrLabel
            Element attrName = attr.previousElementSibling().child(0);
            System.out.print(attrName.text());
            // get rating
            String attrRating = attr.attr("style");
            System.out.println(" = " + attrRating);
        }
    }
}

