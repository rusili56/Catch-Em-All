package nyc.c4q.rusili.catchemall.Network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by rusili on 12/13/16.
 */

public interface PokeService {

    @GET("pokemon/1")
    Call<ApiModel> getPokemon1();

    @GET
    Call<ApiModel> getPokemon2(@Url String url);
}
