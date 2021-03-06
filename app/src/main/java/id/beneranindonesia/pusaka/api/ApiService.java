package id.beneranindonesia.pusaka.api;

import java.util.HashMap;

import id.beneranindonesia.pusaka.models.ContentDetail;
import id.beneranindonesia.pusaka.models.Contents;
import id.beneranindonesia.pusaka.models.User;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("/token")
    Call<Token_1> getToken(@FieldMap HashMap<String, String> body);

    @FormUrlEncoded
    @POST("/auth/signin")
    Call<User> login(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("/auth/signup")
    Call<User> register(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("/content/list")
    Call<Contents> getMissions(@FieldMap HashMap<String, String> body);

    @FormUrlEncoded
    @POST("/content/get")
    Call<ContentDetail> getMissionDetail(@FieldMap HashMap<String, String> params);

}
