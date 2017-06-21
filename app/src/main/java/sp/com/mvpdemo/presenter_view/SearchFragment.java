package sp.com.mvpdemo.presenter_view;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;
import sp.com.mvpdemo.R;
import sp.com.mvpdemo.config.CommonConfig;
import sp.com.mvpdemo.my_views.AutoFeedView;
import sp.com.mvpdemo.utils.FragmentUtils;
import sp.com.mvpdemo.utils.SoftInputUtils;

/**
 * Created by songyuan on 2017/6/19.
 */

public class SearchFragment extends Fragment implements SearchContact.View {
    private Activity activity;
    private SearchContact.Presenter mPresenter;

    @Bind(R.id.et_query_input)
    public EditText et_query_input;

    @Bind(R.id.afv_history_kw)
    public AutoFeedView afv_history_kw;

    private String kw;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
        activity = this.getActivity();

        et_query_input.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                    SoftInputUtils.hideSoftInput(activity, et_query_input);
                    kw = et_query_input.getText().toString();
                    et_query_input.setText("");
                    TextView textView = new TextView(activity);
                    textView.setBackgroundResource(R.drawable.kw_bg);
                    textView.setText(kw);
                    afv_history_kw.addView(textView);
                    //TODO 保存关键字到数据库

                    toResult(kw);
                }


                return false;
            }
        });

        afv_history_kw.setOnItemClickListener(new AutoFeedView.OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view) {
                String historyKw = ((TextView) view).getText().toString();
                et_query_input.setText(historyKw);
                toResult(historyKw);
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        SoftInputUtils.openSoftInput(activity);
    }

    @Override
    public void setPresenter(SearchContact.Presenter presenter) {
        mPresenter = presenter;
    }


    private void toResult(String kw) {
        FragmentUtils.switchFragment(activity, this, activity.getFragmentManager().findFragmentByTag(CommonConfig.RESULT_FRAGMENT_TAG), CommonConfig.RESULT_FRAGMENT_TAG)
                .commit();

    }

    public String getKw() {
        return kw;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            SoftInputUtils.openSoftInput(activity);
        }
    }


}
