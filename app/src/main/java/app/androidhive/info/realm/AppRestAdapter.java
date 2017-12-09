package app.androidhive.info.realm;



import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by sheelu on 23/11/15.
 */
public class AppRestAdapter {

    public static RestAdapter getRestAdapter() {
        RestAdapter.Builder restAdapter =  new RestAdapter.Builder();
        restAdapter.setEndpoint("http://10.0.1.14:8080/OneAssistWeb");
        restAdapter.setClient(new OkClient(new OkHttpClient()));
        //set log levels only in debug mode
        restAdapter.setLogLevel(RestAdapter.LogLevel.FULL);
        return restAdapter.build();
    }

}
