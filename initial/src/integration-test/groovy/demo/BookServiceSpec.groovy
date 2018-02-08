package demo

import grails.plugins.elasticsearch.ElasticSearchAdminService
import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Shared
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class BookServiceSpec extends Specification {

    BookService bookService
    SessionFactory sessionFactory

    private Long setupData() {
        new Book([title: 'Making Java Groovy', author: 'Ken Kousen', href: 'http://www.manning.com/kousen/', image: 'books/Kousen-MJG.png', about: 'Make Java development easier by adding Groovy. Each chapter focuses on a task Java developers do, like building, testing, or working with databases or restful web services, and shows ways Groovy can make those tasks easier.'],).save(flush: true, failOnError: true)
        new Book([title: 'Groovy in Action, 2nd Edition', author: 'Dierk König, Guillaume Laforge, Paul King, Cédric Champeau, Hamlet D\'Arcy, Erik Pragt, and Jon Skeet', href: 'http://www.manning.com/koenig2/', image: 'books/regina.png', about: 'This is the undisputed, definitive reference on the Groovy language, authored by core members of the development team.']).save(flush: true, failOnError: true)
        Book book = new Book([title: 'Groovy for Domain-Specific Languages', author: 'Fergal Dearle', href: 'http://www.packtpub.com/groovy-for-domain-specific-languages-dsl/book', image: 'books/gdsl.jpg', about: 'Learn how Groovy can help Java developers easily build domain-specific languages into their applications.']).save(flush: true, failOnError: true)
        new Book([title: 'Groovy 2 Cookbook', author: 'Andrey Adamovitch, Luciano Fiandeso', href: 'http://www.packtpub.com/groovy-2-cookbook/book', image: 'books/g2cook.jpg', about: 'This book contains more than 90 recipes that use the powerful features of Groovy 2 to develop solutions to everyday programming challenges.']).save(flush: true, failOnError: true)
        new Book([title: 'Programming Groovy 2', author: 'Venkat Subramaniam', href: 'http://pragprog.com/book/vslg2/programming-groovy-2', image: 'books/vslg2.jpg', about: 'This book helps experienced Java developers learn to use Groovy 2, from the basics of the language to its latest advances.']).save(flush: true, failOnError: true)
        book.id
    }

    void "test get"() {
        setupData()

        expect:
        bookService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Book> bookList = bookService.list(max: 2, offset: 2)

        then:
        bookList.size() == 2
        bookList[0].title == 'Making Java Groovy'
        bookList[1].title == 'Groovy in Action, 2nd Edition'
    }

    void "test count"() {
        setupData()

        expect:
        bookService.count() == 5
    }

    void "test delete"() {
        Long bookId = setupData()

        expect:
        bookService.count() == 5

        when:
        bookService.delete(bookId)
        sessionFactory.currentSession.flush()

        then:
        bookService.count() == 4
    }

    void "test save"() {
        when:
        Book book = new Book([title: 'Programming Grails', author: 'Burt Beckwith', href: 'http://shop.oreilly.com/product/0636920024750.do', image: 'books/bbeckwith_cover.gif', about: 'Dig deeper into Grails architecture and discover how this application framework works its magic.'])
        bookService.save(book)

        then:
        book.id != null
    }
}
