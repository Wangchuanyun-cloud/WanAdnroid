package com.example.newgankio.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.newgankio.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoInternetActivity extends AppCompatActivity {

    @BindView(R.id.no_internet)
    Button noInternet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_interner);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.no_internet)
    public void onClick() {
        Intent intent = new Intent(NoInternetActivity.this,HomePageActivity.class);
        startActivity(intent);
        finish();
    }
}
