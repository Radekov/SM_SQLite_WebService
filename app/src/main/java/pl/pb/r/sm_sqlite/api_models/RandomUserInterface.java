package pl.pb.r.sm_sqlite.api_models;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Radosław Naruszewicz on 2016-12-12.
 */

public interface RandomUserInterface {
    @GET("?datatype=json")
    Call<JsonModel> getUser();
}
