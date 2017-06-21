package sp.com.mvpdemo.utils;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;

import sp.com.mvpdemo.R;


/**
 * Created by songyuan on 2017/6/20.
 */

public class FragmentUtils {

    public static FragmentTransaction switchFragment(Activity activity, Fragment from, Fragment to, String toTag) {
        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
        if (!to.isAdded()) {
            transaction.hide(from).add(R.id.fl_main, to, toTag);
        } else {
            transaction.hide(from).show(to);
        }
        return transaction;
    }


}
