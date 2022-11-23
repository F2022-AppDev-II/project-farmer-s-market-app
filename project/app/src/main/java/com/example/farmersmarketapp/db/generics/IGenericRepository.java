package com.example.farmersmarketapp.db.generics;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface IGenericRepository<T> {
    LiveData<List> getAll();
    void insert(T obj);
    void update(T obj);
    void deleteAll();
    void delete(T obj);
}
