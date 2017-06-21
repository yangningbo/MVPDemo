package sp.com.mvpdemo.modle;

/**
 * Created by songyuan on 2017/6/20.
 */

public class Author {
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                '}';
    }
}
