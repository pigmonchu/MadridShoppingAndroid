package com.digestivethinking.madridshops.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digestivethinking.madridshops.R;
import com.digestivethinking.madridshops.domain.model.Shop;
import com.digestivethinking.madridshops.domain.model.Shops;
import com.digestivethinking.madridshops.views.OnElementClick;
import com.digestivethinking.madridshops.views.ShopRowViewHolder;

public class ShopsAdapter extends RecyclerView.Adapter<ShopRowViewHolder>{

    private Shops shops;
    private LayoutInflater inflater;
    private OnElementClick<Shop> listener;

    public ShopsAdapter(@NonNull final Shops shops, @NonNull final Context context) {
        this.shops = shops;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ShopRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_shop, parent, false);

        ShopRowViewHolder viewHolder = new ShopRowViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ShopRowViewHolder shopRow, final int position) {
        final Shop shop = this.shops.getAt(position);
        shopRow.setShop(shop);

        shopRow.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.clickedOn(shop, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (this.shops != null) {
            return this.shops.size();
        }
        return 0;
    }

    public void setOnClickListener(OnElementClick<Shop> listener) {
        this.listener = listener;
    }
}
