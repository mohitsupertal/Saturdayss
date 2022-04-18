package com.shock.saturdaylifestyle.data.remote.entity

/*
* {
    "success": false,
    "message": "Auth Failed",
    "messageTitle": null,
    "data": null,
    "responseTime": "0 ms."
}
* */
data class RegisterEntity (
    val success : Boolean,
    val message : String,
    val messageTitle : String,
    val data : String,
    val responseTime : String,
)