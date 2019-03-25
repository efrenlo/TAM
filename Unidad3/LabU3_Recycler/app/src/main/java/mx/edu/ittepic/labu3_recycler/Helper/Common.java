package mx.edu.ittepic.labu3_recycler.Helper;

import mx.edu.ittepic.labu3_recycler.Remote.IMenuRequest;
import mx.edu.ittepic.labu3_recycler.Remote.RetrofitClient;

public class Common {

    public static IMenuRequest getMenuRequest(){
        return RetrofitClient.getClient("https://api.androidhive.info/").create(IMenuRequest.class);
    }
}
