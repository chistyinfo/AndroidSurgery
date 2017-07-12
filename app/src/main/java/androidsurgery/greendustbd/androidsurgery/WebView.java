package androidsurgery.greendustbd.androidsurgery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebViewClient;

public class WebView extends AppCompatActivity {
    private android.webkit.WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = (android.webkit.WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String gsmUrl = extras.getString("GSM");
            String andUrl = extras.getString("AND");

            if (gsmUrl != null)
                webView.loadUrl(gsmUrl);
            else if (andUrl != null)
                webView.loadUrl(andUrl);
        }
    }
}
