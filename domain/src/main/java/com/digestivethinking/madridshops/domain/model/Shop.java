package com.digestivethinking.madridshops.domain.model;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class Shop implements Serializable {

    private long id;
    private String address;
    private String description_es;
    private String description_en;
    
    private float gps_lat;
    private float gps_lon;
    //var gps_img: NSData?
    private String img_url;
    //var img: NSData?
    private String logo_img_url;
    //var logo_img: NSData?
    //var logo_img_40: NSData?
    private String name;
    private String opening_hours_es;
    private String opening_hours_en;
    private String url;


    public static Shop of(long id, String name) {
        Shop shop = new Shop();

        shop.setId(id);
        shop.setName(name);

        return shop;
    }

    private Shop() {

    }

    public long getId() {
        return id;
    }

    public Shop setId(@NonNull final long id) {
        this.id = id;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Shop setAddress(@NonNull final String address) {
        this.address = address;
        return this;
    }

    public String getDescription_es() {
        return description_es;
    }

    public Shop setDescription_es(@NonNull final String description_es) {
        this.description_es = description_es;
        return this;
    }

    public String getDescription_en() {
        return description_en;
    }

    public Shop setDescription_en(@NonNull final String description_en) {
        this.description_en = description_en;
        return this;
    }

    public float getGps_lat() {
        return gps_lat;
    }

    public Shop setGps_lat(@NonNull final float gps_lat) {
        this.gps_lat = gps_lat;
        return this;
    }

    public float getGps_lon() {
        return gps_lon;
    }

    public Shop setGps_lon(@NonNull final float gps_lon) {
        this.gps_lon = gps_lon;
        return this;
    }

    public String getImg_url() {
        return img_url;
    }

    public Shop setImg_url(@NonNull final String img_url) {
        this.img_url = img_url;
        return this;
    }

    public String getLogo_img_url() {
        return logo_img_url;
    }

    public Shop setLogo_img_url(@NonNull final String logo_img_url) {
        this.logo_img_url = logo_img_url;
        return this;
    }

    public String getName() {
        return name;
    }

    public Shop setName(@NonNull final String name) {
        this.name = name;
        return this;
    }

    public String getOpening_hours_es() {
        return opening_hours_es;
    }

    public Shop setOpening_hours_es(@NonNull final String opening_hours_es) {
        this.opening_hours_es = opening_hours_es;
        return this;
    }

    public String getOpening_hours_en() {
        return opening_hours_en;
    }

    public Shop setOpening_hours_en(@NonNull final String opening_hours_en) {
        this.opening_hours_en = opening_hours_en;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Shop setUrl(@NonNull final String url) {
        this.url = url;
        return this;
    }
}

