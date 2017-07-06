package com.digestivethinking.madridshops.domain.model;

import java.util.List;

public interface ShopsIterable {
    int size();
    Shop getAt(long index);
    List<Shop> getAll();
}
