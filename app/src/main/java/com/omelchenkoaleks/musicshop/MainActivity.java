package com.omelchenkoaleks.musicshop;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    TextView priceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        priceTextView = findViewById(R.id.price_tv);

        mSpinner = findViewById(R.id.spinner);
        mSpinner.setOnItemSelectedListener(this);

        mSpinnerArrayList = new ArrayList();
        mSpinnerArrayList.add("guitar");
        mSpinnerArrayList.add("drums");
        mSpinnerArrayList.add("keyboard");

        mSpinnerAdapter = new ArrayAdapter(
                this, android.R.layout.simple_spinner_item, mSpinnerArrayList);
        mSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mSpinnerAdapter);

        mGoodsMap = new HashMap();
        mGoodsMap.put("guitar", 500.0);
        mGoodsMap.put("drums", 3050.0);
        mGoodsMap.put("keyboard", 5500.0);

    }

    public void increaseQuantity(View view) {
        quantity = quantity + 1;
        TextView quantityTextView = findViewById(R.id.quantity_tv);
        quantityTextView.setText("" + quantity);
        priceTextView.setText("" + mPrice * quantity);
    }

    public void decreaseQuantity(View view) {
        quantity = quantity - 1;
        if (quantity < 0) {
            quantity = 0;
        }
        TextView quantityTextView = findViewById(R.id.quantity_tv);
        quantityTextView.setText("" + quantity);
        priceTextView.setText("" + mPrice * quantity);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mGoodsName = mSpinner.getSelectedItem().toString();
        mPrice = (double) mGoodsMap.get(mGoodsName);
        priceTextView.setText("" + mPrice * quantity);

        ImageView goodsImageView = findViewById(R.id.goods_iv);
        if (mGoodsName.equals("guitar")) {
            goodsImageView.setImageResource(R.drawable.guitar);
        } else if (mGoodsName.equals("drums")) {
            goodsImageView.setImageResource(R.drawable.drums);
        } else if (mGoodsName.equals("keyboard")) {
            goodsImageView.setImageResource(R.drawable.keyboard);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
