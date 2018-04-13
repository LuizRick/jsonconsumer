package com.example.luizl.jsonconsumer

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.ProgressBar
import com.example.luizl.jsonconsumer.dominio.UserList
import com.example.luizl.jsonconsumer.dominio.Usuario
import com.example.luizl.jsonconsumer.services.UserService
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val retroFit = Retrofit.Builder()
                .baseUrl(" https://s3-sa-east-1.amazonaws.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retroFit.create(UserService::class.java)
        val users = service.listUsers()
        var lista = findViewById<ListView>(R.id.list)
        val pbar = findViewById<ProgressBar>(R.id.progressBar)
        doAsync {
            showUsers(this@MainActivity,lista,service)
        }
        val button = findViewById<Button>(R.id.btnRefresh)
        button.setOnClickListener{
            doAsync {
                uiThread {
                    pbar.visibility = View.VISIBLE
                }
                Thread.sleep(1000)
                showUsers(this@MainActivity,lista,service)
                uiThread {
                    pbar.visibility = View.GONE
                }
            }
        }
    }

    fun showUsers(activity:Activity,lista:ListView,service:UserService){
        service.listUsers().enqueue(object : Callback<UserList> {
            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
                val adapter:ArrayAdapter<Usuario> = ArrayAdapter(
                        activity,
                        android.R.layout.simple_list_item_1,
                        response.body()?.usuarios
                )
                lista.adapter = adapter
            }

            override fun onFailure(call: Call<UserList>, t: Throwable) {

            }
        })
    }
}
