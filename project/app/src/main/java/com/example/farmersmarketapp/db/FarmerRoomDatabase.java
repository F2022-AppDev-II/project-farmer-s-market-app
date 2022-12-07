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
import com.example.farmersmarketapp.enums.ImageType;
import com.example.farmersmarketapp.enums.ProductCategory;

@Database(entities = {Product.class, CartItem.class}, version = 23, exportSchema = true)
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
                            .allowMainThreadQueries()
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

        private ProductDao productDao;
        private CartItemDao cartItemDao;

        private static Product[] products = {
                new Product("Apple", "apple", "Bob", ProductCategory.FRUIT.ordinal(), ImageType.Apple.ordinal(), 1.99),
                new Product("Apple", "apple", "Nick", ProductCategory.FRUIT.ordinal(), ImageType.Apple.ordinal(), 1.99),
                new Product("Apple", "apple", "David", ProductCategory.FRUIT.ordinal(), ImageType.Apple.ordinal(), 1.99),
                new Product("Apple", "apple", "Pops", ProductCategory.FRUIT.ordinal(), ImageType.Apple.ordinal(), 1.99),
                new Product("Apple", "apple", "Big Mama", ProductCategory.FRUIT.ordinal(), ImageType.Apple.ordinal(), 1.99),
                new Product("Apple", "apple", "Luffy", ProductCategory.FRUIT.ordinal(), ImageType.Apple.ordinal(), 1.99),
                new Product("Apple", "apple", "Tony", ProductCategory.FRUIT.ordinal(), ImageType.Apple.ordinal(), 1.99),
                new Product("Apple", "apple", "Oliver", ProductCategory.FRUIT.ordinal(), ImageType.Apple.ordinal(), 1.99),
                new Product("Apple", "apple", "Bobby", ProductCategory.FRUIT.ordinal(), ImageType.Apple.ordinal(), 1.99),
        };

        PopulateDbAsync(FarmerRoomDatabase db){
            productDao = db.productDao();
            cartItemDao = db.cartItemDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            if (productDao.getAnyProduct().length == 0){
                for (int i = 0; i < products.length; i++){
                    Product p = products[i];
                    productDao.insert(p);
                }
            }
            return null;
        }
    }
}
