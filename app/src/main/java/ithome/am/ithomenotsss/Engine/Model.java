package ithome.am.ithomenotsss.Engine;

public class Model {
    private long id;
    private String name;
    private String occupation;
    private String image;

    public Model() {
    }

    public Model(long id, String name, String occupation, String image) {
        this.id = id;
        this.name = name;
        this.occupation = occupation;
        this.image = image;
    }

    public Model(String name, String occupation, String image) {
        this.name = name;
        this.occupation = occupation;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
