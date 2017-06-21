package sp.com.mvpdemo.base_mvp;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by songyuan on 2017/6/20.
 */

public interface BaseView <T> {
    void setPresenter(T presenter);
}
