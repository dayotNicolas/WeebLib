package com.example.weebLib.domain.models

data class Book (val title: String, val author: String){
    init {
        require(title.isNotEmpty())
        require(author.isNotEmpty())
    }

    override fun toString(): String {
        return "Book(title='$title', author='$author')"
    }
}

fun bookExists(book: Book?): Boolean {
    return  book != null
}