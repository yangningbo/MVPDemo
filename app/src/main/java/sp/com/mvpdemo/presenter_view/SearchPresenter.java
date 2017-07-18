package sp.com.mvpdemo.presenter_view;

import android.content.Context;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
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
    public void saveKwToDB(final Context context, final String kw) {
        GreenDaoUtils.getDaoSession(context)
                .getKeywordDao()
                .queryBuilder()
                .where(KeywordDao.Properties.Kw.eq(kw))
                .rx()
                .list()
                .subscribe(new Action1<List<Keyword>>() {
                               @Override
                               public void call(List<Keyword> list) {
                                   if (list.size() != 0) {
                                       GreenDaoUtils.getDaoSession(context)
                                               .getKeywordDao()
                                               .delete(list.get(0));
                                   }
                                   Keyword keyword = new Keyword();
                                   keyword.setKw(kw);
                                   GreenDaoUtils.getDaoSession(context)
                                           .getKeywordDao()
                                           .insert(keyword);
                               }
                           },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                throwable.printStackTrace();
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
