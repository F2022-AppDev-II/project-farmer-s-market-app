package com.example.farmersmarketapp.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.farmersmarketapp.db.generics.GenericRepository;
import com.example.farmersmarketapp.db.models.CartItem;

import java.util.List;

public class CartItemRepository extends GenericRepository<CartItem> {

    private CartItemDao cartItemDao;
    private LiveData<List<CartItem>> allCartItems;

    public CartItemRepository(Application app){
        super(app);

        this.cartItemDao = db.cartItemDao();
        this.allCartItems = cartItemDao.getAll();
    }

    public LiveData<List<CartItem>> getAllCartItems(){
        return this.allCartItems;
    }

    public CartItem getCartItem(int id){
        return cartItemDao.getCartItem(id);
    }

    @Override
    public void insert(CartItem cartItem){
        new insertAsyncTask(cartItemDao).execute(cartItem);
        cartItemDao.setLineTotal(cartItem.getId(), cartItem.getProductId());
    }

    @Override
    public void update(CartItem cartItem){
        new updateAsyncTask(cartItemDao).execute(cartItem);
        cartItemDao.setLineTotal(cartItem.getId(), cartItem.getProductId());
    }

    @Override
    public void deleteAll(){
        new deleteAllAsyncTask(cartItemDao).execute();
    }

    @Override
    public void delete(CartItem cartItem){
        new deleteAsyncTask(cartItemDao).execute(cartItem);
    }

    @Override
    public int getSize(){
        return cartItemDao.getSize();
    }

    private static class insertAsyncTask extends AsyncTask<CartItem, Void, Void>{
        private CartItemDao cartItemDao;

        insertAsyncTask(CartItemDao dao){
            cartItemDao = dao;
        }

        @Override
        protected Void doInBackground(CartItem... cartItems) {
            cartItemDao.insert(cartItems[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<CartItem, Void, Void>{
        private CartItemDao cartItemDao;

        updateAsyncTask(CartItemDao dao){
            cartItemDao = dao;
        }

        @Override
        protected Void doInBackground(CartItem... cartItems) {
            cartItemDao.update(cartItems[0]);
            return null;
        }
    }

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void>{
        private CartItemDao cartItemDao;

        deleteAllAsyncTask(CartItemDao dao){
            cartItemDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            cartItemDao.deleteAll();
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<CartItem, Void, Void>{
        private CartItemDao cartItemDao;

        deleteAsyncTask(CartItemDao dao){
            cartItemDao = dao;
        }

        @Override
        protected Void doInBackground(CartItem... cartItems) {
            cartItemDao.delete(cartItems[0]);
            return null;
        }
    }
}
