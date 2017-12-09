package app.androidhive.info.realm.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;


import java.util.List;

import app.androidhive.info.realm.AppImageDisplayOptions;
import app.androidhive.info.realm.OfferDTO;
import app.androidhive.info.realm.R;

/**
 * Created by sheelu on 21/10/15.
 */
public class CardOffersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<OfferDTO> cardOffers;
    private Context context;
    private DisplayImageOptions displayImageOptions;
    private ImageLoader imageLoader;

    private final int VIEW_ITEM_CARD = 1;
    private final int VIEW_ITEM_PROGRESS = 0;

    public CardOffersAdapter(Context context, List<OfferDTO> cardOffers) {
        this.context = context;
        imageLoader = ImageLoader.getInstance();
        this.cardOffers = cardOffers;
        displayImageOptions = AppImageDisplayOptions.getCachedInDiskAndDelayDIO();
    }

    @Override

    public int getItemViewType(int position) {
        return cardOffers.get(position) != null? VIEW_ITEM_CARD: VIEW_ITEM_PROGRESS;
    }

    // Provide a reference to the views for each data item
   // Complex data items may need more than one view per item, and
   // you provide access to all the views for a data item in a view holder
    private static class CardOfferItemViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        TextView  offerLocationTxv;
        TextView  offerValidityTxv;
        TextView  offerStoreNameTxv;
        //ImageView offerStoreImageView;
        TextView  offerPerTxv;
        TextView  offerAddressTxv;

        CardOfferItemViewHolder(View v) {
            super(v);
            offerLocationTxv = (TextView) v.findViewById(R.id.offer_location_txv);
            offerValidityTxv = (TextView) v.findViewById(R.id.offer_validty_txv);
            offerStoreNameTxv = (TextView) v.findViewById(R.id.offer_store_name_txv);
            offerPerTxv = (TextView) v.findViewById(R.id.offer_per_txv);
            //offerStoreImageView = v.findViewById(R.id.store_image_view);
            offerAddressTxv = (TextView) v.findViewById(R.id.store_address_txv);

        }
    }

    static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;
        ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.waiting_pb);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        RecyclerView.ViewHolder vh;
        if(viewType == VIEW_ITEM_CARD) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_card_offers, parent, false);
            // set the view's size, margins, paddings and layout parameters

            vh = new CardOfferItemViewHolder(v);
        }
        else
        {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.dialog_waiting, parent, false);
            vh = new ProgressViewHolder(v);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        //reset Views
        if(holder instanceof CardOfferItemViewHolder) {
            //first set all elements to null
           final CardOfferItemViewHolder cardOfferItemViewHolder = (CardOfferItemViewHolder) holder;

            OfferDTO offerDTO = cardOffers.get(position);

            //offer location
            String location = offerDTO.getOfferLocation();
            cardOfferItemViewHolder.offerLocationTxv.setText(location);

            //valdity
            String validity = offerDTO.getOfferValidity();
            cardOfferItemViewHolder.offerValidityTxv.setText(validity);
            //store name
            String store_name = offerDTO.getOfferStoreName();
            cardOfferItemViewHolder.offerStoreNameTxv.setText(store_name);
            //offer per
            String offer_per = offerDTO.getOfferPercent();
            cardOfferItemViewHolder.offerPerTxv.setText(offer_per);
            //offer address
            String offerAddress = offerDTO.getOfferAddress();
            cardOfferItemViewHolder.offerAddressTxv.setText(offerAddress);

        }
        else
            ((ProgressViewHolder)holder).progressBar.setIndeterminate(true);

    }

    @Override
    public int getItemCount() {
        return cardOffers.size();
    }

    public void addItems(final List<OfferDTO> list) {
        list.addAll(list);
        notifyDataSetChanged();
    }

    public void add(OfferDTO offerDTO) {
        cardOffers.add(offerDTO);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        cardOffers.remove(position);
        notifyDataSetChanged();
    }

    public void removeAll() {
        cardOffers.clear();
        notifyDataSetChanged();
    }

}
