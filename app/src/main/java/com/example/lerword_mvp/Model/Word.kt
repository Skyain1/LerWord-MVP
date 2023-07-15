package com.example.lerword_mvp.Model

/*
 * Created by Skyain1 on 24.06.2023.
 */

data class Word(
    val Id: Int,
    val Title: String,
    val Theme: String,
    val Translate: String,
    var CheckCount: Int,
    var know: Boolean = false
)
