package app.androidhive.info.realm.activity;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.yayandroid.locationmanager.base.LocationBaseActivity;
import com.yayandroid.locationmanager.configuration.Configurations;
import com.yayandroid.locationmanager.configuration.LocationConfiguration;
import com.yayandroid.locationmanager.constants.FailType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.androidhive.info.realm.AppImageDisplayOptions;
import app.androidhive.info.realm.AppRestAdapter;
import app.androidhive.info.realm.BuildConfig;
import app.androidhive.info.realm.DefaultAPI;
import app.androidhive.info.realm.OfferDTO;
import app.androidhive.info.realm.R;
import app.androidhive.info.realm.adapters.CardOffersAdapter;
import app.androidhive.info.realm.helper.EndlessRecyclerOnScrollListener;
import app.androidhive.info.realm.helper.OperationalCityHelper;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CardOffersActivity extends LocationBaseActivity {

    private EndlessRecyclerOnScrollListener scrollListener;
    private Context context;
    private RecyclerView mRecyclerView;
    private int pageNo;
    private int totalPages;
    LinearLayout loadingLl;

    @Override
    public LocationConfiguration getLocationConfiguration() {
        return  Configurations.defaultConfiguration("Give permission",
                "enable location services");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_offers);
        //set image loading configuartion
        AppImageDisplayOptions.setUILImageLoadingConfig(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        loadingLl = (LinearLayout) findViewById(R.id.search_loading_ll);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        scrollListener = new EndlessRecyclerOnScrollListener(mLayoutManager) {

            @Override
            public void onLoadMore(int current_page) {

                //loadMoreOffers(current_page);
            }

        };

        mRecyclerView.addOnScrollListener(scrollListener);

        pageNo = 1;
        totalPages = 0;
        showLoadingDialog();
      //  offersWebService("Delhi NCR");
    }

    public void onResume(){
        super.onResume();
        getLocation();
    }

    public void onPause(){
        super.onPause();
    }


    private void offersUI(List<OfferDTO> offerDTOS){
        int count = offerDTOS.size();
        totalPages = (int) Math.ceil(count / (double) 10);
        CardOffersAdapter adapter = new CardOffersAdapter(this, offerDTOS);
        mRecyclerView.setAdapter(adapter);
    }

    private void offersWebService(String locality) {
        showLoadingDialog();
        RestAdapter restadapter = AppRestAdapter.getRestAdapter();
        DefaultAPI api = restadapter.create(DefaultAPI.class);
        Map<String, String> params = new HashMap<>();
        params.put("location", locality);

        api.getCardOffers(params, new Callback<List<OfferDTO>>() {

            @Override
            public void success(List<OfferDTO> offerDTO, Response response) {
                dismissLoadingDialog();
                offersUI(offerDTO);
            }

            @Override
            public void failure(RetrofitError error) {
                dismissLoadingDialog();
                Toast.makeText(CardOffersActivity.this, "error fetching resposne!",
                        Toast.LENGTH_SHORT).show();
            }
        }
        );


    }


    //This method used to show loading dialog...
    private void showLoadingDialog() {
        loadingLl.setVisibility(View.VISIBLE);
    }

    //This method used to dismiss loading dialog...
    private void dismissLoadingDialog() {
        loadingLl.setVisibility(View.GONE);
    }


    @Override
    public void onLocationChanged(Location location) {
        dismissLoadingDialog();
        String locality = OperationalCityHelper.getDetectedOperationalCity(this, location);
        Toast.makeText(this,"You are in :" + locality, Toast.LENGTH_SHORT ).show();
        offersWebService(locality);
    }

    @Override
    public void onLocationFailed(int type) {
        dismissLoadingDialog();

        switch (type) {
            case FailType.PERMISSION_DENIED:
                showPermissionLocationSnackbar("Enable Permission!");
                //set default operational city India
                break;

            default:
                showPermissionLocationSnackbar("Error in getting location!");
                break;
        }
    }

    private void showPermissionLocationSnackbar(String message) {
        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.parent), message, Snackbar.LENGTH_INDEFINITE);
        snackbar.setActionTextColor(getResources().getColor(R.color.colorPrimary));
        snackbar.setAction("Enable", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(
                        android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + BuildConfig.APPLICATION_ID)));
            }
        });
        snackbar.show();
    }
}
