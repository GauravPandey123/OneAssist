package app.androidhive.info.realm.realm;


import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import app.androidhive.info.realm.model.Offer;
import io.realm.Realm;
import io.realm.RealmResults;


/**
 * Created by Gaurav Pandey on 09-12-2017.
 */
public class RealmController {

    private static RealmController instance;
    private final Realm realm;

    public RealmController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {

        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity) {

        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {

        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {

        return instance;
    }

    public Realm getRealm() {

        return realm;
    }

    //Refresh the realm istance
    public void refresh() {

        realm.refresh();
    }

    //clear all objects from Offer.class
    public void clearAll() {

        realm.beginTransaction();
        realm.clear(Offer.class);
        realm.commitTransaction();
    }

    //find all objects in the Offer.class
    public RealmResults<Offer> getBooks() {

        return realm.where(Offer.class).findAll();
    }

    //query a single item with the given id
    public Offer getBook(String id) {

        return realm.where(Offer.class).equalTo("id", id).findFirst();
    }

    //check if Offer.class is empty
    public boolean hasBooks() {

        return !realm.allObjects(Offer.class).isEmpty();
    }

    //query example
    public RealmResults<Offer> queryedBooks() {

        return realm.where(Offer.class)
                .contains("author", "Author 0")
                .or()
                .contains("title", "Realm")
                .findAll();

    }
}
