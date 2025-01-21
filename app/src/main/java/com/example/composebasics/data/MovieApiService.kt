package com.example.composebasics.data
import android.util.Log
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    @GET("/3/discover/movie")
   suspend fun getMovieList(@Query("include_adult") include_adult: Boolean = true,
                            @Query("include_video") include_video: Boolean = false,
                            @Query("page") site: Int = 1,
                            @Query("sort_by") sort_by:String ="popularity.desc",
                            @Header("accept") accept:String ="application/json",
                            @Header("Authorization") auth:String ="Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhZmU1M2QwNzUwNzExYmZmZjY1MjM2MTM4MThhOWY2OSIsIm5iZiI6MTczMTY1NTY3OC42OTI5OTk4LCJzdWIiOiI2NzM2ZjdmZWNlNDk0MTAwMzJjMWVjMTUiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.jl8mwHKE00kdiNA2Q9XYR-sG_Olk2ySjDPiYEFcyp7w"
    ):MovieResponse
}
class MovieApi{
    val api = RetrofitObject.api

    suspend fun getMovieList():NetworkResult<MovieResponse>{
       return ApiHandlerFunctions.execute({
            api.getMovieList()
        },{
            Log.i("MovieApi","$it")
            it
        })
    }

}
