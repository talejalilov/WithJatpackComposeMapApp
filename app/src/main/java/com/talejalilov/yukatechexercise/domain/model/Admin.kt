package com.talejalilov.yukatechexercise.domain.model

data class Admin(
    var name :String  ="",
    var username :String ="",
    var userId :String = "",
    var email :String  = "",
    var password :String  = "",
    var users : List<String> = emptyList(),

)
