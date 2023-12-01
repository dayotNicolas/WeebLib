package com.example.weebLib.domain.models
import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.*
import org.junit.jupiter.api.Test

class BookTests {

    @Test
    fun `is book instanciated`() {
        // Arrange
        val title = "titre1"
        val author = "author1"

        // Act
        val book = Book(title = title, author = author)

        // Assert
        assertThat(book.title).isNotNull()
        assertThat(book.title).isEqualTo("titre1")
        assertThat(book.author).isEqualTo("author1")
    }

    @Test
    fun `require title not to be empty`() {

        assertFailure { Book(title = "", author = "Stephen King") }
                .hasMessage("title must not be empty")

    }

    @Test
    fun `require author not to be empty`() {

        assertFailure { Book(title = "it", author = "") }
                .hasMessage("author must not be empty")

    }

    @Test
    fun `require anything not to be empty`() {

        assertFailure { Book(title = "", author = "") }
                .hasMessage("title must not be empty")

    }

}