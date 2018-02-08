package demo

import grails.plugins.elasticsearch.ElasticSearchService
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class BookController {

    BookService bookService
    ElasticSearchService elasticSearchService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE", search: "GET"]

    def search(String q) {
        log.error(q)
        if (!q) {
            respond status: NOT_FOUND
            return
        }
        def result = elasticSearchService.search(q, [indices: Book, types: Book, score: true])
        respond result
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond bookService.list(params), model: [bookCount: bookService.count()]
    }

    def show(Long id) {
        respond bookService.get(id)
    }

    def save(Book book) {
        if (book == null) {
            render status: NOT_FOUND
            return
        }

        try {
            bookService.save(book)
        } catch (ValidationException e) {
            respond book.errors, view: 'create'
            return
        }

        respond book, [status: CREATED, view: "show"]
    }

    def update(Book book) {
        if (book == null) {
            render status: NOT_FOUND
            return
        }

        try {
            bookService.save(book)
        } catch (ValidationException e) {
            respond book.errors, view: 'edit'
            return
        }

        respond book, [status: OK, view: "show"]
    }

    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        bookService.delete(id)

        render status: NO_CONTENT
    }
}
