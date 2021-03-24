package br.sscode.todomovies4.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.sscode.todomovies4.R
import br.sscode.todomovies4.data.remote.api.APIClient
import br.sscode.todomovies4.data.remote.model.Movie
import br.sscode.todomovies4.data.remote.model.SimilarMovie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        try {
//            APIClient().getMovieService()?.getMovie()?.enqueue(object: Callback<Movie>{
//                override fun onFailure(call: Call<Movie>?, t: Throwable?) {
//                    Toast.makeText(this@MainActivity, "ERRO", Toast.LENGTH_LONG).show()
//                }
//
//                override fun onResponse(call: Call<Movie>?, response: Response<Movie>?) {
//                    Toast.makeText(this@MainActivity, "SUCESSO", Toast.LENGTH_LONG).show()
//                }
//            })
//        } catch (exception: Exception) {
//            Log.e("ERRO", exception.printStackTrace().toString())
//        }
    }
}