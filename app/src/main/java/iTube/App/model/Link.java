package iTube.App.model;

public class Link {
    private String Url;

    public Link(String Url)
    {
        this.Url = Url;
    }
    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
