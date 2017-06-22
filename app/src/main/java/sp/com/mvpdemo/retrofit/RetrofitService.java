package sp.com.mvpdemo.retrofit;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;
import sp.com.mvpdemo.modle.MusicBean;

/**
 * Created by songyuan on 2017/6/22.
 */

public interface RetrofitService {
    @GET("v2/music/search")
    Observable<JsonObject> getDouBanMusicList(@QueryMap(encoded = true) Map<String, Object> paramMap);
}
