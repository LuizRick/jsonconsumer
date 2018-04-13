package com.example.luizl.jsonconsumer.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

import com.example.luizl.jsonconsumer.dominio.UserList;
import com.example.luizl.jsonconsumer.dominio.Usuario;

import org.json.JSONObject;

/**
 * Created by luizl on 12/04/2018.
 */

public interface UserService {

    @GET("/pontotel-docs/data.json")
    Call<UserList> listUsers();
}
