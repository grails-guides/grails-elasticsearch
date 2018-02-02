package demo

import groovy.transform.CompileStatic

@CompileStatic
class BookController {

    BookSearchService bookSearchService

    def index(String query) {
        if ( !query ) {
            render status: 422
            return
        }
        List<Book> books = bookSearchService.findAllByQuery(query)

        [books: books]
    }

}