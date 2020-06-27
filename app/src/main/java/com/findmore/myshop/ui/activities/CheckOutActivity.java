package com.findmore.myshop.ui.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.findmore.myshop.R;
import com.findmore.myshop.models.Address;
import com.findmore.myshop.models.CartItem;
import com.findmore.myshop.models.UserProfile;
import com.findmore.myshop.ui.fragments.home.HomeViewModel;

public class CheckOutActivity extends AppCompatActivity {

    private Toolbar toolbar;
    Button btnPayment;
    private CartItem cartItem;
    private UserProfile userProfile;
    private HomeViewModel checkoutViewModel;
    private View lyAddress;
    private ImageView imgEdit;
    private Button btnNewAddress;

    private TextView tvProfileName,tvAddress,tvPhoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        checkoutViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        //===============setup toolbar====================================
        toolbar = (Toolbar)  findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar); //toolbar id
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("CHECKOUT");
        //=========================================================
        lyAddress=findViewById(R.id.ly_Address);
        tvProfileName=findViewById(R.id.tvName);
        tvAddress=findViewById(R.id.tvAddress);
        tvPhoneNumber=findViewById(R.id.tvPhoneNum);
        imgEdit=findViewById(R.id.imgEdit);
        btnNewAddress=findViewById(R.id.btn_NewAddress);

        checkoutViewModel.getProfile(this).observe(this, new Observer<UserProfile>() {
            @Override
            public void onChanged(@Nullable UserProfile data) {
                userProfile=data;
                setAddressView(data);
            }
        });

        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Edit address is not enabled.", Toast.LENGTH_LONG).show();
            }
        });

        btnNewAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Add address is not enabled.", Toast.LENGTH_LONG).show();
            }
        });
        checkoutViewModel.getCartData(this).observe(this, new Observer<CartItem>() {
            @Override
            public void onChanged(@Nullable CartItem data) {
                cartItem =data;
                if (data != null) {

                }
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(R.anim.right_out, R.anim.left_enter);
            }
        });

        //==================================================
        btnPayment= findViewById(R.id.btn_Payment);

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "orderid:14585", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CheckOutActivity.this, SuccessOrderActivity.class);
                intent.putExtra("order_id", "14585");
                startActivity(intent);
                overridePendingTransition(R.anim.right_enter, R.anim.left_out);
            }
        });

    }

    @SuppressLint("SetTextI18n")
    private void setAddressView(UserProfile userProfile) {
        if (userProfile!=null){
            tvProfileName.setText(userProfile.getFirstName() + " "+ userProfile.getLastName());
            if (userProfile.getCountryCode() !=null && userProfile.getPhoneNumber() !=null ){
                tvPhoneNumber.setText(userProfile.getCountryCode() + userProfile.getPhoneNumber());
            }
            if (userProfile.getAddress() !=null){
                for (Address address: userProfile.getAddress()) {
                    if (address.getIsshippingAddress()){
                        lyAddress.setVisibility(View.VISIBLE);
                        tvAddress.setText(address.getAddressLine());
                    }
                }
            }else{
                lyAddress.setVisibility(View.GONE);
            }
        }

    }
}
