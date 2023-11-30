package com.example.weebLib.domain.useCases

import assertk.assertThat
import assertk.assertions.*
import com.example.weebLib.domain.models.Book
import com.example.weebLib.domain.ports.BookPorts
import io.mockk.MockKAnnotations.init
import io.mockk.every
import io.mockk.impl.annotations.MockK
import net.jqwik.api.ForAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import assertk.assertions.containsExactly
import io.mockk.InternalPlatformDsl.toArray
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import net.jqwik.api.Property
import net.jqwik.api.constraints.StringLength
import net.jqwik.api.constraints.AlphaChars
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class BookUseCasesTests {
    @InjectMockKs
    private lateinit var bookUseCases: BookUseCases
    @MockK
    private lateinit var mock: BookPorts

    @BeforeEach
    fun setUp() {
        init(this)
    }

    @Test
    fun `all books have title`() {
        // Arrange
        val listBooks = listOf(Book("atitre1", "author1"), Book("btitre2", "author2"))

        every { mock.getAll() } answers { listBooks }

        // Act
        val listBook = bookUseCases.getAllBooks()

        // Assert
        assertThat(listBook.map { it.title }).doesNotContain("")
    }

    @Test
    fun `all books have author`() {
        // Arrange
        val listBooks = listOf(Book("atitre1", "author1"), Book("btitre2", "author2"))

        every { mock.getAll() } answers { listBooks }

        // Act
        val listBook = bookUseCases.getAllBooks()

        // Assert
        assertThat(listBook.map { it.author }).doesNotContain("")
    }

    @Test
    fun `list of books is in alphabetical order`() {
        // Arrange
        val listBooks = listOf(Book("ctitre1", "author1"), Book("btitre2", "author2"), Book("atitre1", "author1"))

        every { mock.getAll() } answers { listBooks }

        // Act
        val listBook = bookUseCases.getAllBooks()
        val sortedListBook = listBook.sortedBy { it.title }

        // Assert
        assertThat(listBook).isEqualTo(sortedListBook)
    }

    @Property
    fun `list stay the same from creation to function output` (
            @ForAll @AlphaChars @StringLength(min = 1) title1:String,
            @ForAll @AlphaChars @StringLength(min = 1) author1:String,
            @ForAll @AlphaChars @StringLength(min = 1) title2:String,
            @ForAll @AlphaChars @StringLength(min = 1) author2:String,
            @ForAll @AlphaChars @StringLength(min = 1) title3:String,
            @ForAll @AlphaChars @StringLength(min = 1) author3:String)
    {

        // Arrange
        val mockA = mockk<BookPorts>()
        val usecase = BookUseCases(mockA)
        val listBooks = arrayOf(Book(title1, author1), Book(title2, author2), Book(title3, author3))

        every { mockA.getAll() } answers { listBooks.toList() }

        // Act
        val listBook = usecase.getAllBooks()

        // Assert
        assertThat(listBook).containsExactlyInAnyOrder(*listBooks)

    }
}