package com.example.farmersmarketapp.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.farmersmarketapp.db.models.CartItem;
import com.example.farmersmarketapp.db.models.Product;

@Database(entities = {Product.class, CartItem.class}, version = 2, exportSchema = true)
public abstract class FarmerRoomDatabase extends RoomDatabase {

    public abstract ProductDao productDao();
    public abstract CartItemDao cartItemDao();

    private static FarmerRoomDatabase INSTANCE;

    public static FarmerRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (FarmerRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FarmerRoomDatabase.class, "farmer_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{

        PopulateDbAsync(FarmerRoomDatabase db){

        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
