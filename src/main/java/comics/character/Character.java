package comics.character;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * The Marvel character domain object.
 */
public class Character {
    private int id;
    private String name;
    private String description;
    private ThumbNail thumbnail;
    private List<Url> urls;
    private String realName;
    private String aliases;
    private String identity;
    private String citizenship;
    private String placeOfBirth;
    private String firstAppearance;
    private String origin;
    private String occupation;
    private String relatives;
    private String groupAffiliation;
    private String education;
    private String physicalAttributes;
    private String powers;
    private String paraphernalia;
    private Map<String, String> powerRatings = new HashMap<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ThumbNail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(ThumbNail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<Url> getUrls() {
        return urls;
    }

    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAliases() {
        return aliases;
    }

    public void setAliases(String aliases) {
        this.aliases = aliases;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getFirstAppearance() {
        return firstAppearance;
    }

    public void setFirstAppearance(String firstAppearance) {
        this.firstAppearance = firstAppearance;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getRelatives() {
        return relatives;
    }

    public void setRelatives(String relatives) {
        this.relatives = relatives;
    }

    public String getGroupAffiliation() {
        return groupAffiliation;
    }

    public void setGroupAffiliation(String groupAffiliation) {
        this.groupAffiliation = groupAffiliation;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getPhysicalAttributes() {
        return physicalAttributes;
    }

    public void setPhysicalAttributes(String physicalAttributes) {
        this.physicalAttributes = physicalAttributes;
    }

    public String getPowers() {
        return powers;
    }

    public void setPowers(String powers) {
        this.powers = powers;
    }

    public String getParaphernalia() {
        return paraphernalia;
    }

    public void setParaphernalia(String paraphernalia) {
        this.paraphernalia = paraphernalia;
    }

    public Map<String, String> getPowerRatings() {
        return powerRatings;
    }

    public void setPowerRatings(Map<String, String> powerRatings) {
        this.powerRatings = powerRatings;
    }

    public String getWikiUrl() {
        String wiki = null;
        for (Url url : urls) {
            if ("wiki".equals(url.getType())) {
                wiki = url.getUrl();
            }
        }
        return wiki;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("int               : %d\n", id));
        sb.append(String.format("name              : %s\n", name));
        sb.append(String.format("description       : %s\n", description));
        sb.append(String.format("thumbnail         : %s\n", thumbnail));
        sb.append(String.format("urls              : %s\n", urls));
        sb.append(String.format("realName          : %s\n", realName));
        sb.append(String.format("aliases           : %s\n", aliases));
        sb.append(String.format("identity          : %s\n", identity));
        sb.append(String.format("citizenship       : %s\n", citizenship));
        sb.append(String.format("placeOfBirth      : %s\n", placeOfBirth));
        sb.append(String.format("firstAppearance   : %s\n", firstAppearance));
        sb.append(String.format("origin            : %s\n", origin));
        sb.append(String.format("occupation        : %s\n", occupation));
        sb.append(String.format("relatives         : %s\n", relatives));
        sb.append(String.format("groupAffiliation  : %s\n", groupAffiliation));
        sb.append(String.format("education         : %s\n", education));
        sb.append(String.format("physicalAttributes: %s\n", physicalAttributes));
        sb.append(String.format("powers            : %s\n", powers));
        sb.append(String.format("paraphernalia     : %s\n", paraphernalia));
        sb.append("powers            :\n");
        Iterator<String> iter = powerRatings.keySet().iterator();
        while (iter.hasNext()) {
            String power = iter.next();
            sb.append(String.format("  %12s: %s\n", power, powerRatings.get(power)));
        }
        return sb.toString();
    }
}
