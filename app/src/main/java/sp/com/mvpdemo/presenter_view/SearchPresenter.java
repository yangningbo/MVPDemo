package sp.com.mvpdemo.presenter_view;

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


}
