package com.example.weebLib.domain.models
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class BookTests {

    @Test
    fun `is book instanciated`() {
        // Arrange
        val book = Book(title = "titre", author = "author")

        // Act
        val res = bookExists(book)

        // Assert
        assertThat(res).isEqualTo(true)
    }

}