package com.digestivethinking.madridshops.domain.model;

public interface ShopsUpdatable {
    void add(Shop shop);
    void delete(Shop shop);
    void update(Shop newShop, long index);
}
