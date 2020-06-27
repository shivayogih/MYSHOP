package com.findmore.myshop.ui.fragments.checkout;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.findmore.myshop.localdb.AppDatabase;
import com.findmore.myshop.models.CartItem;

public class CheckViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;
    private AppDatabase appDatabase;
    private MutableLiveData<CartItem> cartdata;
    public CheckViewModel(Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
        appDatabase = AppDatabase.getDatabase(this.getApplication());
    }

    public LiveData<String> getText() {
        return mText;
    }
}