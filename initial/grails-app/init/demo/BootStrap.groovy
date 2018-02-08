package demo

import groovy.transform.CompileStatic

@CompileStatic
class BootStrap {

    public final static List<Map<String, String> > GRAILS_BOOKS = [
            [title: 'Grails 3 - Step by Step', author: 'Cristian Olaru', href: 'https://grailsthreebook.com/', image: 'books/grails_3_step_by_step.png', about: 'Learn how a complete greenfield application can be implemented quickly and efficiently with Grails 3 using profiles and plugins. Use the sample application that accompanies the book as an example.'],
            [title: 'Practical Grails 3', author: ' Eric Helgeson', href: 'https://www.grails3book.com/', image: 'books/pratical-grails-3-book-cover.png', about: 'Learn the fundamental concepts behind building Grails applications with the first book dedicated to Grails 3. Real, up-to-date code examples are provided, so you can easily follow along.'],
            [title: 'Falando de Grails', author: 'Henrique Lobo Weissmann', href: 'http://www.casadocodigo.com.br/products/livro-grails', image: 'books/grails_weissmann.png', about: 'This is the best reference on Grails 2.5 and 3.0 written in Portuguese. It&#39;s a great guide to the framework, dealing with details that many users tend to ignore.'],
            [title: 'Grails Goodness Notebook', author: 'Hubert A. Klein Ikkink', href: 'https://leanpub.com/grails-goodness-notebook', image: 'books/grailsgood.png', about: 'Experience the Grails framework through code snippets. Discover (hidden) Grails features through code examples and short articles. The articles and code will get you started quickly and provide deeper insight into Grails.'],
            [title: 'The Definitive Guide to Grails 2', author: 'Jeff Scott Brown and Graeme Rocher', href: 'http://www.apress.com/9781430243779', image: 'books/grocher_jbrown_cover.jpg', about: 'As the title states, this is the definitive reference on the Grails framework, authored by core members of the development team.'],
            [title: 'Grails in Action', author: 'Glen Smith and Peter Ledbrook', href: 'http://www.manning.com/gsmith2/', image: 'books/gsmith2_cover150.jpg', about: 'The second edition of Grails in Action is a comprehensive introduction to Grails 2 focused on helping you become super-productive fast.'],
            [title: 'Grails 2: A Quick-Start Guide', author: 'Dave Klein and Ben Klein', href: 'http://www.amazon.com/gp/product/1937785777?tag=misa09-20', image: 'books/bklein_cover.jpg', about: 'This revised and updated edition shows you how to use Grails by iteratively building a unique, working application.'],
            [title: 'Programming Grails', author: 'Burt Beckwith', href: 'http://shop.oreilly.com/product/0636920024750.do', image: 'books/bbeckwith_cover.gif', about: 'Dig deeper into Grails architecture and discover how this application framework works its magic.']
    ] as List<Map<String, String> >


    public final static List<Map<String, String> > GROOVY_BOOKS = [
            [title: 'Making Java Groovy', author: 'Ken Kousen', href: 'http://www.manning.com/kousen/', image: 'books/Kousen-MJG.png', about: 'Make Java development easier by adding Groovy. Each chapter focuses on a task Java developers do, like building, testing, or working with databases or restful web services, and shows ways Groovy can make those tasks easier.'],
            [title: 'Groovy in Action, 2nd Edition', author: 'Dierk König, Guillaume Laforge, Paul King, Cédric Champeau, Hamlet D\'Arcy, Erik Pragt, and Jon Skeet', href: 'http://www.manning.com/koenig2/', image: 'books/regina.png', about: 'This is the undisputed, definitive reference on the Groovy language, authored by core members of the development team.'],
            [title: 'Groovy for Domain-Specific Languages', author: 'Fergal Dearle', href: 'http://www.packtpub.com/groovy-for-domain-specific-languages-dsl/book', image: 'books/gdsl.jpg', about: 'Learn how Groovy can help Java developers easily build domain-specific languages into their applications.'],
            [title: 'Groovy 2 Cookbook', author: 'Andrey Adamovitch, Luciano Fiandeso', href: 'http://www.packtpub.com/groovy-2-cookbook/book', image: 'books/g2cook.jpg', about: 'This book contains more than 90 recipes that use the powerful features of Groovy 2 to develop solutions to everyday programming challenges.'],
            [title: 'Programming Groovy 2', author: 'Venkat Subramaniam', href: 'http://pragprog.com/book/vslg2/programming-groovy-2', image: 'books/vslg2.jpg', about: 'This book helps experienced Java developers learn to use Groovy 2, from the basics of the language to its latest advances.']
    ] as List<Map<String, String> >

    BookService bookService

    def init = { servletContext ->
        for (Map<String, String> bookInfo : (GRAILS_BOOKS + GROOVY_BOOKS)) {
            bookService.save(bookInfo.title, bookInfo.author, bookInfo.about, bookInfo.href, bookInfo.image)
        }
    }

}
