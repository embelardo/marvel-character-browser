package data;

public class SearchResult {
    private String id;
    private String name;
    private String description;
    private ThumbNail thumbnail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
