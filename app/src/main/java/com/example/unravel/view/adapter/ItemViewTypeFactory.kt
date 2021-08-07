package com.example.unravel.view.adapter

interface ItemViewTypeFactory {

    fun type(viewTypeFactory: ViewTypeFactory): Int
}