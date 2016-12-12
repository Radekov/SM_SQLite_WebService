package pl.pb.r.sm_sqlite.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import pl.pb.r.sm_sqlite.R;
import pl.pb.r.sm_sqlite.adapters.JsonAdapter;
import pl.pb.r.sm_sqlite.api_models.JsonModel;
import pl.pb.r.sm_sqlite.api_models.RandomUserInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main2Activity extends AppCompatActivity {

    private final static String BASE_URL = "https://randomuser.me/api/";
    private RecyclerView recyclerView;
    private JsonAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = ((RecyclerView) findViewById(R.id.recyclerViewUser));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new JsonAdapter(this);
        recyclerView.setAdapter(adapter);
        getNewUser();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                getNewUser();

            }
        });
    }

    private static Retrofit retrofit = getRetrofitInstance();
    private static RandomUserInterface userApi = getRandomUserApiInstance();

    private static Retrofit getRetrofitInstance() {
        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return retrofit;
    }

    private static RandomUserInterface getRandomUserApiInstance() {
        if (userApi == null)
            userApi = getRetrofitInstance().create(RandomUserInterface.class);
        return userApi;
    }

    private void getNewUser(){
        Call<JsonModel> call = getRandomUserApiInstance().getUser();
        Log.d("getNewUser",call.request().url().toString());
        call.enqueue(new Callback<JsonModel>() {
            @Override
            public void onResponse(Call<JsonModel> call, Response<JsonModel> response) {
                adapter.addJson(response.body());
            }

            @Override
            public void onFailure(Call<JsonModel> call, Throwable t) {
                Log.w("onFailure","Something wrong");
                t.printStackTrace();
            }
        });
    }

}
