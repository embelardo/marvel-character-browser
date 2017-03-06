package comics.character;

/**
 * Marvel character details include the URL path to a thumbnail picture.
 */
public class ThumbNail {
    private String path;
    private String extension;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public String toString() {
        return String.format("path: %s, extension: %s", path, extension);
    }
}
