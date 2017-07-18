package sp.com.mvpdemo.modle;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by songyuan on 2017/6/21.
 */
@Entity
public class Keyword {
    @Id
    private Long id;
    @Unique
    @NotNull
    private String kw;
    @Generated(hash = 718127131)
    public Keyword(Long id, @NotNull String kw) {
        this.id = id;
        this.kw = kw;
    }
    @Generated(hash = 1812362044)
    public Keyword() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getKw() {
        return this.kw;
    }
    public void setKw(String kw) {
        this.kw = kw;
    }



}
