package com.digestivethinking.madridshops.activities;

import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.digestivethinking.madridshops.R;
import com.digestivethinking.madridshops.domain.model.Shop;
import com.digestivethinking.madridshops.util.Constants;
import com.digestivethinking.madridshops.util.StaticMapImage;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopDetailActivity extends AppCompatActivity {

    @BindView(R.id.activity_shop_detail__address) TextView address;
    @BindView(R.id.activity_shop_detail__description) TextView description;
    @BindView(R.id.activity_shop_detail__image) ImageView shopImage;
    @BindView(R.id.activity_shop_detail__name) TextView name;
    @BindView(R.id.activity_shop_detail__logo) ImageView shopLogo;
    @BindView(R.id.activity_shop_detail__map) ImageView map;
    @BindView(R.id.activity_shop_detail__opening_hours) TextView openingHours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null) {
            Shop shop = (Shop) intent.getSerializableExtra(Constants.INTENT_SHOP_DETAIL);
            name.setText(shop.getName());
            address.setText(shop.getAddress());
            description.setText(shop.getDescription_en());
            openingHours.setText(shop.getOpening_hours_en());
            Picasso.with(this)
                    .load(shop.getImg_url())
                    .placeholder(R.drawable.icono_blanco)
                    .into(shopImage);

            String staticMapUrl = StaticMapImage.getMapImageUrl(shop);
            Picasso.with(this)
                    .load(staticMapUrl)
                    .placeholder(R.drawable.static_map)
                    .into(map);

            Picasso.with(this)
                    .load(shop.getLogo_img_url())
                    .placeholder(R.drawable.online_shop)
                    .into(shopLogo);
        }
    }
}
