package sp.com.mvpdemo.presenter_view;


import sp.com.mvpdemo.base_mvp.BasePresenter;
import sp.com.mvpdemo.base_mvp.BaseView;

/**
 * Created by songyuan on 2017/6/20.
 */

public class SearchContact {
    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<Presenter> {

    }
}
