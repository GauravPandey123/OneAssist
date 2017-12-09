package app.androidhive.info.realm.adapters;

import android.content.Context;

import app.androidhive.info.realm.model.Offer;
import io.realm.RealmResults;

/**
 * Created by Gaurav Pandey on 09-12-2017.
 */
public class RealmOffersAdapter extends RealmModelAdapter<Offer> {

    public RealmOffersAdapter(Context context, RealmResults<Offer> realmResults, boolean automaticUpdate) {

        super(context, realmResults, automaticUpdate);
    }
}