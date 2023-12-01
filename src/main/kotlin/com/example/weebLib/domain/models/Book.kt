package com.example.weebLib.domain.models

data class Book (val title: String, val author: String){
    init {
        require(title.isNotEmpty()) { "title must not be empty" }
        require(author.isNotEmpty()) { "author must not be empty" }
    }

    override fun toString(): String {
        return "Book(title='$title', author='$author')"
    }
}