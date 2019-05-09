package com.omelchenkoaleks.musicshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int quantity = 0;
    Spinner mSpinner;
    ArrayList mSpinnerArrayList;
    ArrayAdapter mSpinnerAdapter;

    HashMap mGoodsMap;
    String mGoodsName;
    double mPrice;
    TextView mPriceTextView;

    EditText mUserNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPriceTextView = findViewById(R.id.price_tv);
        mUserNameEditText = findViewById(R.id.name_et);

        createSpinner();

        createMap();
    }

    void createSpinner() {
        mSpinner = findViewById(R.id.spinner);
        mSpinner.setOnItemSelectedListener(this);

        mSpinnerArrayList = new ArrayList();
        mSpinnerArrayList.add("guitar");
        mSpinnerArrayList.add("drums");
        mSpinnerArrayList.add("keyboard");

        mSpinnerAdapter = new ArrayAdapter(
                this, android.R.layout.simple_spinner_item, mSpinnerArrayList);
        // устанавливаем выпадающий список
        mSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // привязываем адаптер к spinner
        mSpinner.setAdapter(mSpinnerAdapter);
    }

    void createMap() {
        mGoodsMap = new HashMap();
        mGoodsMap.put("guitar", 500.0);
        mGoodsMap.put("drums", 3050.0);
        mGoodsMap.put("keyboard", 5500.0);
    }

    public void increaseQuantity(View view) {
        quantity = quantity + 1;
        TextView quantityTextView = findViewById(R.id.quantity_tv);
        quantityTextView.setText("" + quantity);
        mPriceTextView.setText("" + mPrice * quantity);
    }

    public void decreaseQuantity(View view) {
        quantity = quantity - 1;
        if (quantity < 0) {
            quantity = 0;
        }
        TextView quantityTextView = findViewById(R.id.quantity_tv);
        quantityTextView.setText("" + quantity);
        mPriceTextView.setText("" + mPrice * quantity);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mGoodsName = mSpinner.getSelectedItem().toString();
        mPrice = (double) mGoodsMap.get(mGoodsName);
        mPriceTextView.setText("" + mPrice * quantity);

        ImageView goodsImageView = findViewById(R.id.goods_iv);
        switch (mGoodsName) {
            case "guitar":
                goodsImageView.setImageResource(R.drawable.guitar);
                break;
            case "drums":
                goodsImageView.setImageResource(R.drawable.drums);
                break;
            case "keyboard":
                goodsImageView.setImageResource(R.drawable.keyboard);
                break;
            default:
                goodsImageView.setImageResource(R.drawable.no_googs);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void addToCart(View view) {
        Order order = new Order();
        order.userName = mUserNameEditText.getText().toString();
        order.goodsName = mGoodsName;
        order.quantity = quantity;
        order.price = mPrice;
        order.orderPrice = quantity * mPrice;

        Intent orderIntent = new Intent(this, OrderActivity.class);
        orderIntent.putExtra("userNameForIntent", order.userName);
        orderIntent.putExtra("goodsNameForIntent", order.goodsName);
        orderIntent.putExtra("quantityForIntent", order.quantity);
        orderIntent.putExtra("priceForIntent", order.price);
        orderIntent.putExtra("orderPriceForIntent", order.orderPrice);

        startActivity(orderIntent);
    }
}
