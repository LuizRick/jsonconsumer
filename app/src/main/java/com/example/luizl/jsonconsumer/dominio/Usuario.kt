package com.example.luizl.jsonconsumer.dominio

/**
 * Created by luizl on 12/04/2018.
 */

class Usuario(
        var id:String,
        var name:String,
        var pwd:String
){
    override fun toString(): String {
        return "ID: ${id}\nName: ${name}\nPwd: ${pwd}"
    }
}