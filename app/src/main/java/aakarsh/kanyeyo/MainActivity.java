package aakarsh.kanyeyo;

import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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
        getData();
    }

    public void getData(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String textUrl = "https://yepi.io/api/quote";
        String imgUrl = "https://yepi.io/api/image";
        queue.start();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, textUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        quote = response;
                        if(quote.length() > 250){
                            getData();

                        } else {


                            tview.setText(quote);
                        }
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

}
