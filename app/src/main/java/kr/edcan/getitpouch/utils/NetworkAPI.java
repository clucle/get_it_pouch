package kr.edcan.getitpouch.utils;

import java.util.ArrayList;

import kr.edcan.getitpouch.models.Costemic;
import kr.edcan.getitpouch.models.Costemics;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Junseok Oh on 2017-04-07.
 */

public interface NetworkAPI {

    /*
    @POST("/api/request/login")
    @FormUrlEncoded
    Call<ArrayList<ResponseBody>> getAsdf(
            @Field("asdf") String asdf
    );*/

    @POST("/rank")
    @FormUrlEncoded
    Call<ResponseBody> getRank(
        @Field("order") String order,
        @Field("age") String age,
        @Field("rank_term") String rankTerm
    );

    @POST("/my_pouch/list")
    @FormUrlEncoded
    Call<Costemics> getCosmetics(
        @Field("user_id") String userId
    );

    @POST("/my_pouch/add_item")
    @FormUrlEncoded
    Boolean addCosmetic(
            @Field("user_id") String userId,
            @Field("product_id") String productId
    );
}
