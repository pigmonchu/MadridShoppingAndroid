package com.digestivethinking.madridshops.domain.managers.db;

import android.support.annotation.NonNull;

import com.digestivethinking.madridshops.domain.model.Shop;

public interface DAOWritable<T> {

    long insert(@NonNull final T element);

    long update(@NonNull final long id, @NonNull final T element);

    long delete(@NonNull final long id);

    long delete(@NonNull final T element);

    void deleteAll();

    long delete(String where, String... whereClause);
}
