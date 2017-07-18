package sp.com.mvpdemo.presenter_view;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import cn.pedant.SweetAlert.SweetAlertDialog;
import sp.com.mvpdemo.R;
import sp.com.mvpdemo.config.CommonConfig;
import sp.com.mvpdemo.utils.FragmentUtils;


public class MainActivity extends FragmentActivity {

    private Activity activity;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;

        fm = this.getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ResultFragment resultFragment = ResultFragment.newInstance();
        ft.add(R.id.fl_main, resultFragment, CommonConfig.RESULT_FRAGMENT_TAG);
        ft.commit();

        new ResultPresenter(resultFragment);

    }


    @Override
    public void onBackPressed() {
        SearchFragment searchFragment = (SearchFragment) getFragmentManager().findFragmentByTag(CommonConfig.SEARCH_FRAGMENT_TAG);
        if (searchFragment != null && searchFragment.isVisible()) {
            FragmentUtils.switchFragment(activity, searchFragment, (ResultFragment) getFragmentManager().findFragmentByTag(CommonConfig.RESULT_FRAGMENT_TAG), CommonConfig.RESULT_FRAGMENT_TAG)
                    .commit();
        } else {
            new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
                    .setContentText("现在退出？")
                    .setConfirmText("确定")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.cancel();
                            MainActivity.super.onBackPressed();
                        }
                    })
                    .setCancelText("取消").show();
        }

    }
}
