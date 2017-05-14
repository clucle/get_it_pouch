package kr.edcan.getitpouch.utils;

import java.util.List;

import kr.edcan.getitpouch.models.Cosmetic;
import kr.edcan.getitpouch.models.Costemics;
import kr.edcan.getitpouch.net.res.Common;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Junseok Oh on 2017-04-07.
 */

public interface NetworkAPI {

    @POST("/rank")
    @FormUrlEncoded
    Call<List<Cosmetic>> getRank(
        @Field("order") String order,
        @Field("age") String age,
        @Field("rank_term") String rankTerm,
        @Field("category") String category
    );

    @POST("scan")
    @FormUrlEncoded
    Call<Cosmetic> scanBarcode(
        @Field("barcode") String barcode
    );

    @POST("/search")
    @FormUrlEncoded
    Call<Cosmetic> searchCosmetic(
        @Field("product_id") String productId
    );

    @POST("/my_pouch/list")
    @FormUrlEncoded
    Call<Costemics> getCosmetics(
        @Field("user_id") String userId
    );

    @POST("/my_pouch/add_item")
    @FormUrlEncoded
    Call<Common> addCosmetic(
        @Field("user_id") String userId,
        @Field("product_id") String productId
    );

    @POST("my_pouch/delete")
    @FormUrlEncoded
    Call<Common> deleteCosmetic(
        @Field("user_id") String userId,
        @Field("product_id") String productId
    );

    @POST("my_pouch/re_buy")
    @FormUrlEncoded
    Call<Common> rebuyCosmetic(
            @Field("user_id") String userId,
            @Field("product_id") String productId
    );
}
