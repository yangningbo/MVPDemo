package sp.com.mvpdemo;

import android.app.Application;

import com.orhanobut.logger.Logger;

import sp.com.mvpdemo.config.CommonConfig;


/**
 * Created by songyuan on 2017/6/20.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init(CommonConfig.LOG_TAG).methodCount(1);
    }
}
