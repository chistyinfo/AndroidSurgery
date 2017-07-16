package androidsurgery.greendustbd.androidsurgery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class WebView extends AppCompatActivity {
    private android.webkit.WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = (android.webkit.WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setHorizontalScrollBarEnabled(false);


        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String gsmUrl = extras.getString("GSM");
            String andUrl = extras.getString("AND");
            String andauUrl = extras.getString("ANDAu");

            if (gsmUrl != null)
                webView.loadUrl(gsmUrl);
            else if (andUrl != null)
                webView.loadUrl(andUrl);
            else if (andauUrl != null)
                webView.loadUrl(andauUrl);
        }


    }


}
