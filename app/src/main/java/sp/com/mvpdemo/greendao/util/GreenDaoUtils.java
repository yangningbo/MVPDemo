package sp.com.mvpdemo.greendao.util;

import android.content.Context;

import sp.com.mvpdemo.greendao.DaoMaster;
import sp.com.mvpdemo.greendao.DaoSession;

/**
 * Created by songyuan on 2017/6/21.
 */

public class GreenDaoUtils {

    private static DaoSession session;
    private static final String DATA_BASE_NAME = "test.db";

    public synchronized static DaoSession getDaoSession(Context context) {
        if (session == null) {
            session = new DaoMaster(new DaoMaster.DevOpenHelper(context, DATA_BASE_NAME).getWritableDb()).newSession();
        }
        return session;
    }


}
