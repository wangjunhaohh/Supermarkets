package bean;

public class image {
    String id;
    String time;
    String photo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhoto() {
        return photo;
    }

    public image() {
    }

    @Override
    public String toString() {
        return "image{" +
                "id='" + id + '\'' +
                ", time='" + time + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
