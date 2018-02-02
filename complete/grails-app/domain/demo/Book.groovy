package demo

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class Book {
    //TODO Add necessary ElasticSearch configuration

    String title
    String about

    static mapping = {
        about type: 'text'
    }
}