package sp.com.mvpdemo.presenter_view;


import sp.com.mvpdemo.base_mvp.BasePresenter;
import sp.com.mvpdemo.base_mvp.BaseView;

/**
 * Created by songyuan on 2017/6/20.
 */

public class ResultContact {
    interface Presenter extends BasePresenter {
        void getMusicListByKw(String kw);
    }

    interface View extends BaseView<Presenter> {
        void showMusicList(Object o);
    }
}
