package com.example.farmersmarketapp.db.generics;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.farmersmarketapp.db.FarmerRoomDatabase;

import java.util.List;

public abstract class GenericRepository<T> implements IGenericRepository<T>{

    protected FarmerRoomDatabase db;

    public GenericRepository(Application app){
        this.db = FarmerRoomDatabase.getDatabase(app);
    }

    @Override
    public LiveData<List> getAll() {
        return null;
    }

    @Override
    public void insert(T obj) {

    }

    @Override
    public void update(T obj) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void delete(T obj) {

    }
}
