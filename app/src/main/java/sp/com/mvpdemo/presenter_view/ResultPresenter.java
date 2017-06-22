package sp.com.mvpdemo.presenter_view;

import com.google.gson.JsonObject;
import com.orhanobut.logger.Logger;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import sp.com.mvpdemo.modle.MusicBean;
import sp.com.mvpdemo.retrofit.RetrofitHelper;
import sp.com.mvpdemo.retrofit.RetrofitService;

/**
 * Created by songyuan on 2017/6/20.
 */

public class ResultPresenter implements ResultContact.Presenter {
    private ResultContact.View mView;

    @Override
    public void start() {

    }

    public ResultPresenter(ResultContact.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getMusicListByKw(String kw) {
        //TODO 获取网络数据
        RetrofitHelper.getInstance()
                .getDouBanMusicList("kw", 0, 10)
                .subscribe(new Subscriber<List<MusicBean>>() {
                    @Override
                    public void onStart() {
                        super.onStart();

                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<MusicBean> musicBeen) {
                        mView.showMusicList(musicBeen);
                    }
                });


    }
}
