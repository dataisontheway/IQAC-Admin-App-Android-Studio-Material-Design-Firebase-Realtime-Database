package info.androidhive.navigationdrawer.activity;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import info.androidhive.navigationdrawer.R;

public class NotificationsActivity extends AppCompatActivity {

    private Context mContext;
    private Activity mActivity;
    //private RelativeLayout mRelativeLayout;
    private WebView mWebView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        mContext = getApplicationContext();

        mActivity = NotificationsActivity.this;

        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#FFFF0000"))
        );

        //mRelativeLayout = (RelativeLayout) findViewById(R.id.rl);
        mWebView = (WebView) findViewById(R.id.web_view);
        mProgressBar = (ProgressBar) findViewById(R.id.pb);

        renderWebPage("https://console.firebase.google.com/project/hope-67614/notification");

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

    }

    protected void renderWebPage(String urlToRender){
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){

                mProgressBar.setVisibility(View.VISIBLE);
            }


        });

        mWebView.setWebChromeClient(new WebChromeClient(){

            public void onProgressChanged(WebView view, int newProgress){
                mProgressBar.setProgress(newProgress);
                if(newProgress == 100){
                    mProgressBar.setVisibility(View.VISIBLE);
                }
            }
        });

        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.loadUrl(urlToRender);
    }

    @Override
    public void onBackPressed()
    {

        if(mWebView.canGoBack())
        {
            mWebView.goBack();
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}