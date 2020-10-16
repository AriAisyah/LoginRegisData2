package com.example.loginregisdata.network

import com.example.loginregisdata.models.DefaultResponse
import com.example.loginregisdata.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {
    @FormUrlEncoded
    @POST("createuser")
    fun createuser(
        @Field("email") email : String,
        @Field("name") name : String,
        @Field("password") password : String,
        @Field("schoool") school : String

    ) : Call<DefaultResponse>

    @FormUrlEncoded
    @POST ("userlogin")
    fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ) : Call<LoginResponse>
}