package com.omelchenkoaleks.musicshop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {
    String[] addresses = {"omelchenkoaleks@gmail.com",};
    String subject = "Order from Music Shop";
    String emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent receivedOrderIntent = getIntent();
        String userName = receivedOrderIntent.getStringExtra("userNameForIntent");
        String goodsName = receivedOrderIntent.getStringExtra("goodsNameForIntent");
        int quantity = receivedOrderIntent.getIntExtra("quantityForIntent", 0);
        double price = receivedOrderIntent.getDoubleExtra("priceForIntent", 0);
        double orderPrice = receivedOrderIntent.getDoubleExtra("orderPriceForIntent", 0);

        emailText = "Customer name: " + userName + "\n" +
                    "Goods name: " + goodsName + "\n" +
                    "Quantity: " + quantity + "\n" +
                    "Price: " + price + "\n" +
                    "Order Price" + orderPrice;

        TextView orderTextView = findViewById(R.id.order_tv);
        orderTextView.setText(emailText);
    }

    public void submitOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
