package com.example.composebasics.data
import android.util.Log
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    @GET("posts")
   suspend fun getMovieList(@Query("order") order: String = "desc",
                            @Query("sort") sort: String = "activity",
                            @Query("site") site: String = "stackoverflow"):StackAnswersPojo
}
class MovieApi(){
    val api = RetrofitObject.api

    suspend fun getMovieList():NetworkResult<StackAnswersPojo>{
       return ApiHandlerFunctions.execute({
            api.getMovieList()
        },{
            Log.i("MovieApi","$it")
            it
        })
    }

}
