package com.example.weebLib.domain.ports
import com.example.weebLib.domain.models.Book

interface BookPorts {

    fun createBook(book:Book)
    fun getAll():List<Book>
}