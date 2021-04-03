package fr.esiea.ex4A;

import fr.esiea.ex4A.user.AgifyData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface AgifyClient {
    @GET("/")
    Call<AgifyData> agify(@Query("name") String user, @Query("country_id") String countryId);
    @GET("/")
    Call<List<AgifyData>> batchAgify(@Query("name[]") List<String> name);
}
