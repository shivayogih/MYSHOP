package com.findmore.myshop.ui.fragments.home

import android.app.Activity
import android.app.Application
import android.os.AsyncTask
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.findmore.myshop.localdb.AppDatabase
import com.findmore.myshop.models.*

import com.findmore.myshop.utils.ReadJsonFile

class HomeViewModel(application: Application?) : AndroidViewModel(application!!) {
    private val mHomedata: MutableLiveData<HomeData> = MutableLiveData()
    private var cartproducts: MutableLiveData<MutableList<CartProducts>?>?
    private var cartdata: MutableLiveData<CartItem>?
    private val appDatabase: AppDatabase
    private val userProfileMd: MutableLiveData<UserProfile>
    private fun cartDatafromDB(){
        try {
            object :
                AsyncTask<Void?, Void?, MutableList<CartProducts>>() {
                override fun onPostExecute(cartProducts1: MutableList<CartProducts>) {
                    super.onPostExecute(cartProducts1)
                    cartproducts!!.value = cartProducts1
                    updateCartData(cartProducts1)
                }

                override fun doInBackground(vararg p0: Void?): MutableList<CartProducts> {
                    return appDatabase.cartModelDao().allCartProductItems
                }
            }.execute()
        } catch (e: Exception){
            e.printStackTrace()
        }
    }




    private fun updateCartData(cartProducts1: List<CartProducts>?) {
        //read the home data from the assets HomeData.json file and update to live data.
        if (cartproducts != null) {
            if (cartdata == null) {
                cartdata = MutableLiveData()
            }
            val mcart = CartItem()
            val mProducts: List<CartProducts>? = cartproducts!!.value
            if (mProducts != null) {
                var count = 0
                var amt = 0
                mcart.setProducts(mProducts)
                for (data in cartproducts!!.value!!) {
                    count += data.product_quantity
                    amt += data.product_total_amount
                }
                mcart.total_cart_items = count
                mcart.total_amount = amt
            }
            cartdata!!.postValue(mcart)
        }
    }

    fun getHomeData(activity: FragmentActivity?): LiveData<HomeData> {
        //read the home data from the assets HomeData.json file and update to live data.
        val homeData = ReadJsonFile.getAssetJsonHomeData(activity)
        mHomedata.value = homeData
        return mHomedata
    }

    fun addToCart(activity: FragmentActivity, mProduct: Products) {
        //read the home data from the assets HomeData.json file and update to live data.
        val cartProducts = cartproducts!!.value
        if (cartProducts != null && cartProducts.size > 0) {
            for (cartProducts1 in cartProducts) {
                if (cartProducts1!!.products!!.productId == mProduct.productId) {
                    cartProducts1.product_quantity = cartProducts1.product_quantity + 1
                    cartProducts1.product_total_amount =
                        cartProducts1.product_quantity * cartProducts1.products!!.productAmount
                    updateCartProduct(cartProducts1)
                    Toast.makeText(
                        activity.applicationContext,
                        mProduct.productLabel + " is already available in cart.",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    val product = CartProducts()
                    product.products = mProduct
                    product.product_quantity = 1
                    addCartProduct(product)
                    cartProducts.add(product)
                    Toast.makeText(
                        activity.applicationContext,
                        mProduct.productLabel + " is added to cart.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        } else {
            val product = CartProducts()
            product.products = mProduct
            product.product_quantity = 1
            addCartProduct(product)
            cartProducts!!.add(product)
            Toast.makeText(
                activity.applicationContext,
                mProduct.productLabel + " is added to cart.",
                Toast.LENGTH_LONG
            ).show()
        }
        cartproducts!!.value = cartProducts
    }

    fun getCartData(activity: FragmentActivity?): LiveData<CartItem> {
        if (cartdata == null) {
            cartdata = MutableLiveData()
            cartproducts = MutableLiveData()
        }
        if (cartproducts == null) {
            cartproducts = MutableLiveData()
        }
        return cartdata!!
    }

    private fun addCartProduct(cartProducts: CartProducts) {
        addAsyncTask(appDatabase).execute(cartProducts)
    }

    private fun updateCartProduct(cartProducts: CartProducts) {
        updateAsyncTask(appDatabase).execute(cartProducts)
    }

    fun deleteItem(cartProducts: CartProducts?, position: Int) {
        if (cartProducts != null) {
            deleteAsyncTask(appDatabase).execute(cartProducts)
            cartproducts!!.value!!.removeAt(position)
            updateCartData(cartproducts!!.value)
        }
    }

    fun deleteAll() {
        deleteAllWordsAsyncTask(appDatabase).execute()
    }

    fun removeqtyCartProduct(cartProducts: CartProducts?, position: Int) {
        if (cartProducts != null) {
            updateAsyncTask(appDatabase).execute(cartProducts)
            cartproducts!!.value!![position].products = cartProducts.products
            cartproducts!!.value!![position].product_quantity = cartProducts.product_quantity
            cartproducts!!.value!![position].product_total_amount =
                cartProducts.product_total_amount
            cartproducts!!.value!![position].cart_productId = cartProducts.cart_productId
            updateCartData(cartproducts!!.value)
        }
    }

    /*    public void addqtyCartProduct(CartProducts cartProducts,int position) {
            if (cartProducts!=null){
            new updateAsyncTask(appDatabase).execute(cartProducts);
            cartproducts.getValue().get(position).setProducts(cartProducts.getProducts());
            cartproducts.getValue().get(position).setProduct_quantity(cartProducts.getProduct_quantity());
            cartproducts.getValue().get(position).setProduct_total_amount(cartProducts.getProduct_total_amount());
            cartproducts.getValue().get(position).setCart_productId(cartProducts.getCart_productId());
            updateCartData(cartproducts.getValue());
        }
    }*/
    fun getProfile(activity: Activity?): LiveData<UserProfile> {
        val userProfile = ReadJsonFile.getProfileData(activity)
        userProfileMd.value = userProfile
        return userProfileMd
    }

    private class addAsyncTask internal constructor(private val db: AppDatabase) :
        AsyncTask<CartProducts?, Void?, Void?>() {

        override fun onPostExecute(aVoid: Void?) {
            super.onPostExecute(aVoid)
        }

        override fun doInBackground(vararg params: CartProducts?): Void? {
            db.cartModelDao().addProducts(params[0])
            return null
        }

    }

    private class updateAsyncTask internal constructor(private val db: AppDatabase) :
        AsyncTask<CartProducts?, Void?, Void?>() {

        override fun onPostExecute(aVoid: Void?) {
            super.onPostExecute(aVoid)
        }
        override fun doInBackground(vararg params: CartProducts?): Void? {
            db.cartModelDao().updateProducts(params[0])
            return null
        }

    }

    private class deleteAsyncTask internal constructor(private val db: AppDatabase) :
        AsyncTask<CartProducts?, Void?, Void?>() {


        override fun onPostExecute(aVoid: Void?) {
            super.onPostExecute(aVoid)
        }

        override fun doInBackground(vararg params: CartProducts?): Void? {
            db.cartModelDao().deleteProduct(params[0])
            return null
        }

    }

    private class deleteAllWordsAsyncTask internal constructor(private val mAsyncTaskDao: AppDatabase) :
        AsyncTask<Void?, Void?, Void?>() {


        override fun doInBackground(vararg voids: Void?): Void? {
            mAsyncTaskDao.cartModelDao().deleteAll()
            return null
        }

    }

    init {
        cartproducts = MutableLiveData()
        cartdata = MutableLiveData()
        userProfileMd = MutableLiveData()
        appDatabase = AppDatabase.getDatabase(getApplication())
        cartDatafromDB()
    }
}