package androidsurgery.greendustbd.androidsurgery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.aapbd.appbajarlib.notification.BusyDialog;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import androidsurgery.greendustbd.androidsurgery.TechInfoVolley.CustomTeachInfoListAdapter;
import androidsurgery.greendustbd.androidsurgery.TechInfoVolley.Teachinfo;

public class Teach extends AppCompatActivity {
    // Log tag
    private static final String TAG = Teach.class.getSimpleName();
    // Movies json url
    private static final String url = "https://greendustbdplus.000webhostapp.com/androidsurgery/teach.json";
    private List<Teachinfo> teachList = new ArrayList<Teachinfo>();
    private ListView tlistView;
    private String[] DetailsArray;
    private Context con;
    private CustomTeachInfoListAdapter tadapter;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teach);
        con = this;
        //native add
        NativeExpressAdView adView = (NativeExpressAdView) findViewById(R.id.adViewte);
        adView.loadAd(new AdRequest.Builder().build());
        tlistView = (ListView) findViewById(R.id.teach_list);
        tadapter = new CustomTeachInfoListAdapter(this, teachList);
        tlistView.setAdapter(tadapter);
        tlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                             @Override
                                             public void onItemClick(AdapterView<?> parent, View view,
                                                                     int position, long id) {
                                                 BitmapDrawable bd = (BitmapDrawable) ((NetworkImageView) view.findViewById(R.id.thumbnail))
                                                         .getDrawable();
                                                 Bitmap bitmap = bd.getBitmap();
                                                 ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                                 bd.getBitmap().compress(Bitmap.CompressFormat.PNG, 100, baos);
                                                 byte[] imgByte = baos.toByteArray();


                                                 Intent intent = new Intent(Teach.this, SingleTipsActivity.class);
                                                 intent.putExtra("Details", DetailsArray[position]);
                                                 intent.putExtra("image", imgByte);
                                                 Teach.this.startActivity(intent);

                                             }
                                         }
        );
        if (isNetworkAvailable()) {

            final BusyDialog busydialog = new BusyDialog(con, true, "Loading........");
            busydialog.show();


            // Creating volley request obj
            JsonArrayRequest teachReq = new JsonArrayRequest(url,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.d(TAG, response.toString());
                            busydialog.dismis();

                            // Parsing json
                            DetailsArray = new String[response.length()];

                            for (int i = 0; i < response.length(); i++) {
                                try {

                                    JSONObject obj = response.getJSONObject(i);
                                    Teachinfo teach = new Teachinfo();
                                    teach.setTitle(obj.getString("title"));
                                    teach.setThumbnailUrl(obj.getString("image"));
                                    teach.setSummary(obj.getString("summary"));
                                    //url capturing form server
                                    DetailsArray[i] = obj.getString("details");

                                    teachList.add(teach);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            // notifying list adapter about data changes
                            // so that it renders the list view with updated data
                            tadapter.notifyDataSetChanged();
//
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());

                    if (busydialog != null) {

                        busydialog.dismis();

                    }

                }
            });
            AppController.getInstance().addToRequestQueue(teachReq);

        } else {

            Toast.makeText(this, "Please check your Internet Connection",
                    Toast.LENGTH_LONG).show();

//            webView = (WebView) findViewById(R.id.wvBk);
//            webView.loadUrl("file:///android_asset/notification.png");

        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void onBackPressed() {

        Intent intent = new Intent(Teach.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

    }
}