package com.digestivethinking.madridshops.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.digestivethinking.madridshops.R;
import com.digestivethinking.madridshops.domain.model.Shop;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

public class ShopRowViewHolder extends RecyclerView.ViewHolder{

    private TextView shopNameTextView;
    private TextView shopOpeningTimeTextView;
    private ImageView backgroundShopImageView;
    private ImageView iconShopImageView;

    WeakReference<Context> context;

    public ShopRowViewHolder(View rowShop) {
        super(rowShop);

        shopNameTextView = (TextView) rowShop.findViewById(R.id.row_shop__shop_name);
        shopOpeningTimeTextView = (TextView) rowShop.findViewById(R.id.row_shop__opening_time);
        backgroundShopImageView = (ImageView) rowShop.findViewById(R.id.row_shop__background_shop);
        iconShopImageView = (ImageView) rowShop.findViewById(R.id.row_shop__icon_shop);

        this.context = new WeakReference<>(rowShop.getContext());

    }

    public void setShop(Shop shop) {
        if (shop == null) {
            return;
        }

        shopNameTextView.setText(shop.getName());
        shopOpeningTimeTextView.setText(shop.getOpening_hours_en());


        Picasso.with(context.get())
                .load(shop.getLogo_img_url())
                .placeholder(R.drawable.icono_blanco) //TODO Interesa poner otro placeHolder...
 //               .networkPolicy(OFFLINE) TODO Esto es para la práctica primero te las bajas todas y las guardas en fichero y luego fuerzas siempre a leer de local
                .into(iconShopImageView);

        Picasso.with(context.get())
                .load(shop.getImg_url())
                .into(backgroundShopImageView);
    }


}
