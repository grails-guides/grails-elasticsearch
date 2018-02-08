package demo

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
    static searchable = {
        title boost: 2.0  // <1>
        about boost: 1.0  // <2>
        except = ['href', 'image', 'imageUrl'] // <3>
    }

    String getImageUrl() {
        if (href && image) {
            return "$href/$image"
        }
    }
}
