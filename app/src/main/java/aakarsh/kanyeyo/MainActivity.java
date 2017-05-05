package aakarsh.kanyeyo;

import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class MainActivity extends AppCompatActivity {

    ImageView iview;
    TextView tview;
    String quote;
    FloatingActionButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iview = (ImageView)findViewById(R.id.imageView);
        tview = (TextView) findViewById(R.id.textView);
        button = (FloatingActionButton) findViewById(R.id.button);
        button.setOnClickListener(buttonListener);
       // getData();
        sendData();
    }

    public void getData(){
        final RequestQueue queue = Volley.newRequestQueue(this);
        //String textUrl = "https://yepi.io/api/quote";
        String textUrl = "https://11e6cb2f.ngrok.io/todos";
        String imgUrl = "https://yepi.io/api/image";
        queue.start();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, textUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        quote = response;

                            System.out.println(quote);
                            tview.setText(quote);
                        }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Do nothing.
                    }
                });

        StringRequest imgUrlRequest = new StringRequest(Request.Method.GET, imgUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        retriveImg(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Do nothing.
                    }
                });


        queue.add(stringRequest);
        queue.add(imgUrlRequest);



    }

    public void retriveImg(String u){
        RequestQueue imgQueue = Volley.newRequestQueue(this);
        imgQueue.start();

        ImageRequest imgRetriveRequest = new ImageRequest(u,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        iview.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {

                    }
                });

imgQueue.add(imgRetriveRequest);


    }

    public View.OnClickListener buttonListener = new View.OnClickListener() {
        public void onClick(View view) {
                getData();

        }

    };


    public void sendData(){
/*
        RequestQueue queue = Volley.newRequestQueue(this);

        LinkedHashMap<String, Object> jsonParams = new LinkedHashMap<String, Object>();

        jsonParams.put("email", "garod@ia.com");
        jsonParams.put("password", "sdsds");
       // jsonParams.put("password", "pass");

        JsonObjectRequest postRequest = new JsonObjectRequest( Request.Method.POST, " http://46cf9e11.ngrok.io/users",

                new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //   Handle Error
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("User-agent", System.getProperty("http.agent"));
                return headers;
            }
        };


        queue.add(postRequest);
*/

        String url = "http://46cf9e11.ngrok.io/users";

        try {
            URL url = new URL(url);


        } catch (Exception e){


        }

    }

    public void exampleVolley(){



    }




}
