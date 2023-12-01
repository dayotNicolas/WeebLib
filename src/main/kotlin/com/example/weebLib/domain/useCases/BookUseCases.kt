package com.example.weebLib.domain.useCases

import com.example.weebLib.domain.models.Book
import com.example.weebLib.domain.ports.BookPorts

class BookUseCases(val bookPorts: BookPorts) {

    fun createBook(title:String, author:String) {
        val book = Book(title=title, author=author)
        return this.bookPorts.createBook(book)
    }

    fun getAllBooks(): List<Book>{
        return this.bookPorts.getAll().sortedBy { it.title }
    }
}