package mx.edu.ittepic.labu3_recycler.Remote;

import mx.edu.ittepic.labu3_recycler.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface IMenuRequest {

    @GET
    Call<List<Item>> getMenuList(@Url String url);
}

