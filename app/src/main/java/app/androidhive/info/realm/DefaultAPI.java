package app.androidhive.info.realm;


import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;

/**
 * Created by sheelu on 29/10/15.
 */
public interface DefaultAPI {

    @GET("/offer")
    void getCardOffers(@QueryMap Map<String, String> params, Callback<List<OfferDTO>> cb);


}
