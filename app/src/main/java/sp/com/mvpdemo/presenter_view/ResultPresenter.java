package sp.com.mvpdemo.presenter_view;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import rx.Subscriber;
import sp.com.mvpdemo.modle.MusicBean;
import sp.com.mvpdemo.retrofit.RetrofitHelper;

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
        final SweetAlertDialog dialog = new SweetAlertDialog(((ResultFragment) mView).getActivity(), SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("loading...");
        RetrofitHelper.getInstance()
                .getDouBanMusicList(kw, 0, 10)
                .subscribe(new Subscriber<List<MusicBean>>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        dialog.show();
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                        dialog.setTitleText("")
                                .setContentText("访问出错，请稍后再试")
                                .show();
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<MusicBean> musicBeen) {
                        if (musicBeen.isEmpty()) {
                            dialog.changeAlertType(SweetAlertDialog.WARNING_TYPE);
                            dialog.setTitleText("")
                                    .setContentText("很抱歉！未搜索到相应歌曲")
                                    .show();
                        } else {
                            dialog.cancel();
                        }
                        mView.showMusicList(musicBeen);
                    }
                });


    }
}
