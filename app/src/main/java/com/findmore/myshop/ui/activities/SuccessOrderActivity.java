package com.findmore.myshop.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.findmore.myshop.R;
import com.findmore.myshop.ui.fragments.home.HomeViewModel;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SuccessOrderActivity extends AppCompatActivity {


    Button btnBack;
    TextView tvOrderId;
    private HomeViewModel successViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_success_order);

        successViewModel  = ViewModelProviders.of(this).get(HomeViewModel.class);

        btnBack=findViewById(R.id.btn_Back);
        Bundle bundle = getIntent().getExtras();
        tvOrderId=findViewById(R.id.tv_OrderId);

        if(bundle.getString("order_id")!= null)
        {
            tvOrderId.setText("Order Id: "+bundle.getString("order_id"));
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                successViewModel.deleteAll();

                finishAffinity();
                Intent intent = new Intent(SuccessOrderActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.right_enter, R.anim.left_out);
            }
        });

    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_out, R.anim.left_enter);
    }
}
