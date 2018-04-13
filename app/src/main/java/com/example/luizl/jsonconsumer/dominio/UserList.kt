package com.example.luizl.jsonconsumer.dominio

import com.google.gson.annotations.SerializedName

/**
 * Created by luizl on 12/04/2018.
 */

class UserList(
    @SerializedName("data")
    val usuarios: List<Usuario>
)
