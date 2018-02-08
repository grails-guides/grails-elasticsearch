package demo

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class Book {

    String title
    String author
    String about

    String href
    String image

    static transients = ['imageUrl']
    static mapping = {
        about type: 'text'
    }

    String getImageUrl() {
        if (href && image) {
            return "$href/$image"
        }
    }
}
