package nyc.c4q.rusili.catchemall.Network;

import android.util.Log;

import nyc.c4q.rusili.catchemall.Activity_Catch;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rusili on 12/13/16.
 */

public class PokeRetrofit {

    public ApiModel[] call(String idInput) {
        final ApiModel[] apiModel = new ApiModel[1];

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        String url = "pokemon/" + idInput;
        PokeService service = retrofit.create(PokeService.class);
        Call<ApiModel> getStuff = service.getPokemon2(url);
        getStuff.enqueue(new Callback<ApiModel>() {
            @Override
            public void onResponse(Call<ApiModel> call, final Response<ApiModel> response) {
                if (response.isSuccessful()) {
                    apiModel[0] = response.body();
                    Log.d("success1", String.valueOf(apiModel[0].getId()));
                    Activity_Catch.addtoDatabase(apiModel[0]);
                }
            }

            @Override
            public void onFailure(Call<ApiModel> call, Throwable t) {
                Log.d("failure", t.toString());
            }
        });
        return apiModel;
    }

}
