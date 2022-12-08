package com.example.farmersmarketapp.db.generics;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SimpleSQLiteQuery;

@Dao
public abstract class GenericDao<T> {
    private final String TABLE_NAME;

    protected GenericDao(String table_name){
        TABLE_NAME = table_name;
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract void insert(T obj);

    @RawQuery
    public abstract int doDeleteAll(SimpleSQLiteQuery sqLiteQuery);

    public void deleteAll(){
        SimpleSQLiteQuery query = new SimpleSQLiteQuery("DELETE FROM " + TABLE_NAME);
        doDeleteAll(query);
    }

    @Delete
    public abstract void delete(T obj);

    @Update
    public abstract void update(T... obj);
}
