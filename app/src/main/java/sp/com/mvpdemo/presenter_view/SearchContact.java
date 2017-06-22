package sp.com.mvpdemo.presenter_view;


import android.content.Context;

import java.util.List;

import rx.Observable;
import sp.com.mvpdemo.base_mvp.BasePresenter;
import sp.com.mvpdemo.base_mvp.BaseView;
import sp.com.mvpdemo.modle.Keyword;

/**
 * Created by songyuan on 2017/6/20.
 */

public class SearchContact {
    interface Presenter extends BasePresenter {
        void saveKwToDB(Context context, String kw);

        Observable<List<Keyword>> getKwFromDB(Context context);
    }

    interface View extends BaseView<Presenter> {

    }
}
