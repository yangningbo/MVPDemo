package sp.com.mvpdemo.presenter_view;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.health.PackageHealthStats;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sp.com.mvpdemo.R;
import sp.com.mvpdemo.config.CommonConfig;
import sp.com.mvpdemo.modle.MusicBean;
import sp.com.mvpdemo.utils.FragmentUtils;

/**
 * Created by songyuan on 2017/6/19.
 */

public class ResultFragment extends Fragment implements ResultContact.View {
    private Activity activity;
    private ResultContact.Presenter mPresenter;

    @Bind(R.id.list_result)
    ListView list_result;

    @Bind(R.id.et_query_click)
    EditText et_query_click;

    public static ResultFragment newInstance() {
        ResultFragment fragment = new ResultFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        ButterKnife.bind(this, view);
        activity = this.getActivity();
        return view;
    }




    @Override
    public void setPresenter(ResultContact.Presenter presenter) {
        mPresenter = presenter;
    }

    @OnClick(R.id.et_query_click)
    public void onClickEtQuery(View view) {
        Fragment fragmentTo = activity.getFragmentManager().findFragmentByTag(CommonConfig.SEARCH_FRAGMENT_TAG);
        if(fragmentTo==null){
            fragmentTo = SearchFragment.newInstance();
            new SearchPresenter((SearchFragment)fragmentTo);
        }
        FragmentUtils.switchFragment(activity, this, fragmentTo, CommonConfig.SEARCH_FRAGMENT_TAG)
                .commit();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            SearchFragment searchFragment = (SearchFragment)activity.getFragmentManager().findFragmentByTag(CommonConfig.SEARCH_FRAGMENT_TAG);
            if (searchFragment != null) {
                String kw = searchFragment.getKw();
                if (kw != null && kw.replace(" ", "") != "") {
                    mPresenter.getMusicListByKw(kw);
                }
            }
        }
    }

    @Override
    public void showMusicList(Object o) {
        List<MusicBean> musicBean = (List<MusicBean>) o;
        Logger.d(musicBean.toString());
    }
}
