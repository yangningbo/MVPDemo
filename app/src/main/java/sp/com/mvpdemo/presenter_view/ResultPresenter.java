package sp.com.mvpdemo.presenter_view;

import com.orhanobut.logger.Logger;

/**
 * Created by songyuan on 2017/6/20.
 */

public class ResultPresenter implements ResultContact.Presenter {
    private ResultContact.View mView;
    @Override
    public void start() {

    }

    public ResultPresenter(ResultContact.View view){
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getMusicListByKw(String kw) {
        //TODO 获取网络数据

    }
}
