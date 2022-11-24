package com.example.farmersmarketapp.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.farmersmarketapp.db.generics.GenericRepository;
import com.example.farmersmarketapp.db.models.Product;

import java.util.List;

public class ProductRepository extends GenericRepository<Product> {

    private ProductDao productDao;
    private LiveData<List<Product>> allProducts;
    private LiveData<List<Product>> allViewedProducts;

    public ProductRepository(Application app) {
        super(app);

        this.productDao = db.productDao();
        this.allProducts = productDao.getAll();
        this.allViewedProducts = productDao.getAllRecentlyViewed();
    }

    public LiveData<List<Product>> getAllProducts() {
        return this.allProducts;
    }

    public LiveData<List<Product>> getAllViewedProducts(){
        return this.allViewedProducts;
    }

    public Product getProduct(int id){
        return productDao.getProduct(id);
    }

    @Override
    public void insert(Product product){
        new insertAsyncTask(productDao).execute(product);
    }

    @Override
    public void update(Product product){
        new updateAsyncTask(productDao).execute(product);
    }

    @Override
    public void deleteAll(){
        new deleteAllAsyncTask(productDao).execute();
    }

    @Override
    public void delete(Product product){
        new deleteAsyncTask(productDao).execute(product);
    }

    @Override
    public int getSize(){
        return productDao.getSize();
    }

    /**
     * Set the specified product to the top of the recently viewed list.
     * @param id The product id.
     */
    public void setToTopViewedPriority(int id){
        productDao.setToTopViewedPriority(id);
    }

    private static class insertAsyncTask extends AsyncTask<Product, Void, Void>{

        private ProductDao productDao;

        insertAsyncTask(ProductDao dao){
            productDao = dao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDao.insert(products[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Product, Void, Void>{

        private ProductDao productDao;

        updateAsyncTask(ProductDao dao){
            productDao = dao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDao.update(products[0]);
            return null;
        }
    }

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void>{

        private ProductDao productDao;

        deleteAllAsyncTask(ProductDao dao){
            productDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            productDao.deleteAll();
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Product, Void, Void>{

        private ProductDao productDao;

        deleteAsyncTask(ProductDao dao){
            productDao = dao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDao.delete(products[0]);
            return null;
        }
    }
}
