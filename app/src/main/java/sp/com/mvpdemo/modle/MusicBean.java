package sp.com.mvpdemo.modle;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by songyuan on 2017/6/20.
 */

public class MusicBean {

    private String id;
    private String title;
    private String image;
    private List<Author> author;
    @SerializedName("mobile_link")
    private String mobileLink;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public String getMobileLink() {
        return mobileLink;
    }

    public void setMobileLink(String mobileLink) {
        this.mobileLink = mobileLink;
    }

    @Override
    public String toString() {
        return "MusicBean{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", author=" + author.get(0).toString() +
                ", mobileLink='" + mobileLink + '\'' +
                '}';
    }
}
