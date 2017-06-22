package sp.com.mvpdemo.retrofit;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.channels.GatheringByteChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import sp.com.mvpdemo.modle.MusicBean;

/**
 * Created by songyuan on 2017/6/22.
 */

public class RetrofitHelper {
    private static final String BASE_URL = "https://api.douban.com/";
    private static final long TIME_OUT = 10000;
    private RetrofitService retrofitService;


    public static RetrofitHelper getInstance() {
        return SingleHolder.INSTANCE;
    }

    private static class SingleHolder {
        private static final RetrofitHelper INSTANCE = new RetrofitHelper();
    }

    private RetrofitHelper() {
        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build();
        Retrofit retrofit = new Retrofit
                .Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        retrofitService = retrofit.create(RetrofitService.class);
    }


    public Observable<List<MusicBean>> getDouBanMusicList(String kw, int start, int count) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("q", kw);
        paramMap.put("start", start);
        paramMap.put("count", count);
        return retrofitService.getDouBanMusicList(paramMap)
                .map(new Func1<JsonObject, List<MusicBean>>() {
                    @Override
                    public List<MusicBean> call(JsonObject jsonObject) {
                        JsonArray musicJA = jsonObject.get("musics").getAsJsonArray();
                        List<MusicBean> musicBeanList = new Gson().fromJson(musicJA, new TypeToken<ArrayList<MusicBean>>() {
                        }.getType());
                        return musicBeanList;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
