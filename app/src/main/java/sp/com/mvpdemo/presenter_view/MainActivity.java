package sp.com.mvpdemo.presenter_view;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import sp.com.mvpdemo.R;
import sp.com.mvpdemo.config.CommonConfig;


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
        ResultFragment resultFragment = new ResultFragment();
        ft.add(R.id.fl_main, resultFragment, CommonConfig.RESULT_FRAGMENT_TAG);
        ft.commit();

        new ResultPresenter(resultFragment);

    }


}
