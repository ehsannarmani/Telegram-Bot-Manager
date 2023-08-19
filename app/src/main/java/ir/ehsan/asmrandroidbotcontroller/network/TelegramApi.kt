package ir.ehsan.asmrandroidbotcontroller.network

import ir.ehsan.asmrandroidbotcontroller.models.Result
import ir.ehsan.asmrandroidbotcontroller.models.Update
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface TelegramApi {
    @GET("getUpdates")
    suspend fun getUpdates(@Query("offset") offset:Long,@Query("timeout") timeout:Int = 60):Response<Result<List<Update>>>

    @GET("getUpdates")
    suspend fun getUpdates(@Query("timeout") timeout:Int = 60):Response<Result<List<Update>>>

    @POST("sendMessage")
    @FormUrlEncoded
    suspend fun sendMessage(
        @Field("chat_id") chatId:String,
        @Field("text") text:String
    ):Response<Result<Any>>

}