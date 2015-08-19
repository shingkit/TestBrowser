package com.jwjx.browser;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.jwjx.browser.fragment.DrawerFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.webview)
    WebView webView;
    @Bind(R.id.swipe)
    SwipeRefreshLayout swipe;
    @Bind(R.id.fl_drawer)
    FrameLayout fl_drawer;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer_layout;

    private ProgressDialog pd;
    private Fragment drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    /**
     * 初始化布局
     */
    private void init() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        drawer = new DrawerFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_drawer, drawer,"DRAWER").commit();

        pd = new ProgressDialog(this);

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer_layout.openDrawer(Gravity.LEFT);
            }
        });
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.reload();
            }
        });
        WebSettings settings = webView.getSettings();
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pd.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pd.dismiss();
                swipe.setRefreshing(false);
            }
        });
        settings.setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                pd.setMessage("加载到" + newProgress + "%");
            }
        });

    }

    public void loadUrl(String str){
        drawer_layout.closeDrawer(Gravity.LEFT);
        webView.loadUrl(str);
    }

}
