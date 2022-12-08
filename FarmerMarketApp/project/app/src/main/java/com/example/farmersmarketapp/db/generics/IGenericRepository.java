package com.example.farmersmarketapp.db.generics;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface IGenericRepository<T> {
    LiveData<List<T>> getAll();
    T get(int id);
    void insert(T obj);
    void update(T obj);
    void deleteAll();
    void delete(T obj);
    int getSize();
}
