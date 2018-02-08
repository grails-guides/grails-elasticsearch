package demo

import grails.compiler.GrailsCompileStatic
import grails.gorm.services.Service

@GrailsCompileStatic
@Service(Book)
interface BookService {

    Book get(Serializable id)

    List<Book> list(Map args)

    Long count()

    void delete(Serializable id)

    Book save(Book book)

    Book save(String title, String author, String about, String href, String image)

}