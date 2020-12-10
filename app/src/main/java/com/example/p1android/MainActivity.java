package com.example.p1android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    ListView listView;

    private List<Usuarios> BuscarUsuariosRequisicao(){
        String urlAPI = "https://jsonplaceholder.typicode.com/users";
        final ArrayList<Usuarios> listaUsuarios = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlAPI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.v("", response);
                    JSONArray array = new JSONArray(response);
                    for(int i = 0; i <= array.length(); i++){
                        JSONObject object = array.getJSONObject(i);
                        Usuarios usuario = new Usuarios(
                                object.getInt("id"),
                                object.getString("email"),
                                object.getJSONObject("address").getString("city")
                        );
                        listaUsuarios.add(usuario);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        queue.add(stringRequest);
        return  listaUsuarios;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.dados);
        final List<Usuarios> listaUsuarios = BuscarUsuariosRequisicao();

        ArrayAdapter<Usuarios> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaUsuarios);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Usuarios usuario = listaUsuarios.get(i);
                Intent intent = new Intent(getApplicationContext(), Acao.class);
                intent.putExtra("user", (Serializable) usuario);
                startActivity(intent);
            }
        });


    }


}