package com.example.newgankio.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.newgankio.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleToWebViewActivity extends AppCompatActivity {

    @BindView(R.id.web_view)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_to_web_view);
        ButterKnife.bind(this);
        WebSettings settings = webView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        webView.setWebViewClient(new WebViewClient());
        Intent intent =getIntent();
        String URL = intent.getStringExtra("link");
        webView.loadUrl(URL);
    }
}
