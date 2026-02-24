package com.example.myapp;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import api.AppController;

public class ApiActivity extends AppCompatActivity {
    TextView tvFajr, tvDhuhr, tvAsr, tvMaghrib, tvIsha, tvDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_api);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvDate = findViewById(R.id.tvDate);
        tvFajr = findViewById(R.id.tvFajr);
        tvDhuhr = findViewById(R.id.tvDhuhr);
        tvAsr = findViewById(R.id.tvAsr);
        tvMaghrib = findViewById(R.id.tvMaghrib);
        tvIsha = findViewById(R.id.tvIsha);
        getAllEntries();
    }
    private void getAllEntries(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String link = AppController.Link;
        JsonObjectRequest js = new JsonObjectRequest(Request.Method.GET, link, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    // 🔹 المستوى الأول
                    int code = response.getInt("code");
                    String status = response.getString("status");

                    JSONObject data = response.getJSONObject("data");

                    // 🔹 timings
                    JSONObject timings = data.getJSONObject("timings");

                    String fajr = timings.getString("Fajr");
                    String dhuhr = timings.getString("Dhuhr");
                    String asr = timings.getString("Asr");
                    String maghrib = timings.getString("Maghrib");
                    String isha = timings.getString("Isha");

                    // 🔹 التاريخ
                    JSONObject dateObj = data.getJSONObject("date");
                    String readableDate = dateObj.getString("readable");
                    JSONObject hijri = dateObj.getJSONObject("hijri");
                    String hijriDate = hijri.getString("date");

                    // 🔹 meta
                    JSONObject meta = data.getJSONObject("meta");
                    String timezone = meta.getString("timezone");
                    // ✅ مثال عرض على TextView
                    tvFajr.setText(fajr);
                    tvDhuhr.setText(dhuhr);
                    tvAsr.setText(asr);
                    tvMaghrib.setText(maghrib);
                    tvIsha.setText(isha);
                    tvDate.setText(readableDate);


                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ApiActivity.this, error+"", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }
        };
        AppController.getInstance().addToRequestQueue(js);

    }


}