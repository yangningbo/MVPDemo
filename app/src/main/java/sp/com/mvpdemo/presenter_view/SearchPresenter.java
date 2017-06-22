package sp.com.mvpdemo.presenter_view;

import android.content.Context;

import com.orhanobut.logger.Logger;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import sp.com.mvpdemo.greendao.auto.KeywordDao;
import sp.com.mvpdemo.greendao.util.GreenDaoUtils;
import sp.com.mvpdemo.modle.Keyword;

/**
 * Created by songyuan on 2017/6/20.
 */

public class SearchPresenter implements SearchContact.Presenter {
    private SearchContact.View mView;

    public SearchPresenter(SearchContact.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }


    @Override
    public void saveKwToDB(final Context context, String kw) {
        final Keyword keyword = new Keyword();
        keyword.setKw(kw);

        GreenDaoUtils.getDaoSession(context)
                .getKeywordDao()
                .queryBuilder()
                .where(KeywordDao.Properties.Kw.eq(kw))
                .rx()
                .list()
                .subscribe(new Subscriber<List<Keyword>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Keyword> keywords) {
                        if (keywords.size() > 0) {
                            GreenDaoUtils.getDaoSession(context)
                                    .getKeywordDao()
                                    .delete(keywords.get(0));
                            GreenDaoUtils.getDaoSession(context)
                                    .getKeywordDao()
                                    .insert(keyword);
                        }
                    }
                });


    }

    @Override
    public Observable<List<Keyword>> getKwFromDB(Context context) {
        return GreenDaoUtils.getDaoSession(context)
                .getKeywordDao().queryBuilder()
                .rx()
                .list()
                .observeOn(AndroidSchedulers.mainThread());
    }
}
