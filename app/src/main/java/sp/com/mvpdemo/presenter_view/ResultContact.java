package sp.com.mvpdemo.presenter_view;


import java.util.List;

import sp.com.mvpdemo.base_mvp.BasePresenter;
import sp.com.mvpdemo.base_mvp.BaseView;
import sp.com.mvpdemo.modle.MusicBean;

/**
 * Created by songyuan on 2017/6/20.
 */

public class ResultContact {
    interface Presenter extends BasePresenter {
        void getMusicListByKw(String kw);
    }

    interface View extends BaseView<Presenter> {
        void showMusicList(List<MusicBean> list);
    }
}
