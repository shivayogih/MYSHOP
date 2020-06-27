package com.findmore.myshop.ui.fragments.cart;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.findmore.myshop.R;
import com.findmore.myshop.adapters.CartItemsAdapter;
import com.findmore.myshop.models.CartItem;
import com.findmore.myshop.models.CartProducts;
import com.findmore.myshop.ui.fragments.home.HomeViewModel;


public class CartFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    private HomeViewModel cartViewModel;
    private CartItem cartItem;
    private RecyclerView cartItemsList;
    private CartItemsAdapter cartItemsAdapter;
    private Toolbar toolbar;
    private View viewCart;
    private View viewEmpty;
    private TextView tv_TotalAmount;
    private Button btnProceed,btnRefresh;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cartViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_cart, container, false);

        toolbar = (Toolbar) root.findViewById(R.id.toolbar_home);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar); //toolbar id
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
         mTitle.setText("CART");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);




        cartItemsList= root.findViewById(R.id.rvCartList);
        viewCart= root.findViewById(R.id.ly_cart);
        viewEmpty=  root.findViewById(R.id.ly_empty);
        tv_TotalAmount=root.findViewById(R.id.tv_TotalAmount);
        btnProceed=root.findViewById(R.id.btn_Proceed);
        btnRefresh=root.findViewById(R.id.btn_Refresh);
        btnProceed.setOnClickListener(this);
        btnRefresh.setOnClickListener(this);

        cartItemsAdapter = new CartItemsAdapter(getActivity(),this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        cartItemsList.setLayoutManager(layoutManager);
        cartItemsList.setAdapter(cartItemsAdapter);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cartViewModel.getCartData(getActivity()).observe(getViewLifecycleOwner(), new Observer<CartItem>() {
            @Override
            public void onChanged(@Nullable CartItem data) {
                cartItem =data;
               if (data != null) {
                    if (data.getProducts() != null && data.getProducts().getValue().size()>0) {
                        cartItemsAdapter.updateData(data.getProducts().getValue());
                        initCartView(data);
                        viewCart.setVisibility(View.VISIBLE);
                        viewEmpty.setVisibility(View.GONE);
                    }else{
                        viewCart.setVisibility(View.GONE);
                        viewEmpty.setVisibility(View.VISIBLE);
                    }
               }else{
                    viewCart.setVisibility(View.GONE);
                    viewEmpty.setVisibility(View.VISIBLE);
               }
            }
        });

    }

    private void initCartView(CartItem data) {
        tv_TotalAmount.setText(String.valueOf(data.getTotal_amount()));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (view!=null){
            try {
                CartProducts product= cartItem.getProducts().getValue().get(position);
                //you can also find out which view was clicked from the Products list from home page
                switch (view.getId()) {
                    case R.id.btn_Remove:
                        //add item cart icon view was clicked
                        if (product.getProduct_quantity() <=1){
                            product.setProduct_quantity(1);
                            showAleart(product,position);
                            return;
                        }else if (product.getProduct_quantity() >1){
                            product.setProduct_quantity(product.getProduct_quantity() -1);
                            product.setProduct_total_amount(product.getProduct_quantity() * product.getProducts().getProductAmount());
                            cartViewModel.removeqtyCartProduct(product,position);
                        }

                        break;
                    case R.id.btn_Add:
                        //add item cart icon view was clicked
                        if (product!= null){
                            if (product.getProduct_quantity()<10)
                            product.setProduct_quantity(1+product.getProduct_quantity());
                            product.setProduct_total_amount(product.getProduct_quantity() * product.getProducts().getProductAmount());
                            cartViewModel.removeqtyCartProduct(product,position);
                        }else {
                            Toast.makeText(getActivity(), "you have reached maximum quantity for this product.", Toast.LENGTH_LONG).show();
                        }
                        break;

                    case R.id.btn_delete:
                        //Remove or delete cart item from list
                        showAleart(product,position);
                        break;
                }
            }catch (Exception e){e.printStackTrace();}

        }
    }

    private void showAleart(final CartProducts product, final int position) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setMessage("Are you sure, You wanted to remove "+ product.getProducts().getProductLabel() +" from Cart");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                cartViewModel.deleteItem(product,position);
                              //  Toast.makeText(getActivity(),"You clicked yes button",Toast.LENGTH_LONG).show();
                            }
                        });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View view) {
        if (view==null){
            return;
        }
        try{
            switch (view.getId()) {
                case R.id.btn_Proceed:
             /*       Intent intent = new Intent(getActivity(), CheckOutActivity.class);
                   // intent.putExtra(EXTRA_MESSAGE, message);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.right_enter, R.anim.left_out);*/
                    break;
                case R.id.btn_Refresh:
                    Toast.makeText(getActivity(), "Refreshed", Toast.LENGTH_LONG).show();
                    break;
            }
        }catch (Exception e){e.printStackTrace();}
    }
}
