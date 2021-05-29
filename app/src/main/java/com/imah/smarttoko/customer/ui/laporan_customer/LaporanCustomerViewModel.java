package com.imah.smarttoko.customer.ui.laporan_customer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LaporanCustomerViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LaporanCustomerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}