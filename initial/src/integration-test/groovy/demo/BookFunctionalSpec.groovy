package demo

import geb.spock.GebSpec
import grails.converters.JSON
import grails.plugins.elasticsearch.ElasticSearchAdminService
import grails.plugins.rest.client.RestBuilder
import grails.testing.mixin.integration.Integration
import grails.transaction.Rollback

import static org.springframework.http.HttpStatus.*

@Integration
@Rollback
class BookFunctionalSpec extends GebSpec {

    RestBuilder getRestBuilder() {
        new RestBuilder()
    }

    String getResourcePath() {
        "${baseUrl}/book"
    }

    Closure getValidJson() {{->
        JSON.parse('''{
  "about": "Learn how a complete greenfield application can be implemented quickly and efficiently with Grails 3 using profiles and plugins. Use the sample application that accompanies the book as an example.",
  "title": "Grails 3 - Step by Step",
  "image": "books/grails_3_step_by_step.png",
  "href": "https://grailsthreebook.com/",
  "author": "Cristian Olaru"
}
''')
    }}

    Closure getInvalidJson() {{->
        JSON.parse('''{
  "image": "books/test.png",
  "href": "https://test.com/",
  "name": "Test"
}
''')
    }}    

    void "Test the index action"() {
        when:"The index action is requested"
        def response = restBuilder.get(resourcePath)

        then:"The response is correct"
        response.status == OK.value()
        response.json == []
    }

    void "Test the save action correctly persists an instance"() {
        when:"The save action is executed with no content"
        def response = restBuilder.post(resourcePath)

        then:"The response is correct"
        response.status == UNPROCESSABLE_ENTITY.value()
        
        when:"The save action is executed with invalid data"
        response = restBuilder.post(resourcePath) {
            json invalidJson
        }           
        then:"The response is correct"
        response.status == UNPROCESSABLE_ENTITY.value()


        when:"The save action is executed with valid data"
        response = restBuilder.post(resourcePath) {
            json validJson
        }        

        then:"The response is correct"
        response.status == CREATED.value()
        response.json.id
        Book.count() == 1
    }

    void "Test the update action correctly updates an instance"() {
        when:"The save action is executed with valid data"
        def response = restBuilder.post(resourcePath) {
            json validJson
        }        

        then:"The response is correct"
        response.status == CREATED.value()
        response.json.id

        when:"The update action is called with invalid data"
        def id = response.json.id
        response = restBuilder.put("$resourcePath/$id") {
            json invalidJson
        }  

        then:"The response is correct"
        response.status == UNPROCESSABLE_ENTITY.value()

        when:"The update action is called with valid data"
        response = restBuilder.put("$resourcePath/$id") {
            json validJson
        }  

        then:"The response is correct"
        response.status == OK.value()        
        response.json

    }    

    void "Test the show action correctly renders an instance"() {
        when:"The save action is executed with valid data"
        def response = restBuilder.post(resourcePath) {
            json validJson
        }        

        then:"The response is correct"
        response.status == CREATED.value()
        response.json.id

        when:"When the show action is called to retrieve a resource"
        def id = response.json.id
        response = restBuilder.get("$resourcePath/$id") 

        then:"The response is correct"
        response.status == OK.value()
        response.json.id == id  
    }  

    void "Test the delete action correctly deletes an instance"() {
        when:"The save action is executed with valid data"
        def response = restBuilder.post(resourcePath) {
            json validJson
        }        

        then:"The response is correct"
        response.status == CREATED.value()
        response.json.id

        when:"When the delete action is executed on an unknown instance"
        def id = response.json.id
        response = restBuilder.delete("$resourcePath")

        then:"The response is correct"
        response.status == NOT_FOUND.value()
        
        when:"When the delete action is executed on an existing instance"
        response = restBuilder.delete("$resourcePath/$id") 

        then:"The response is correct"
        response.status == NO_CONTENT.value()        
        !Book.get(id)
    }

    void "Test the search action correctly searches"() {
        when:"The save action is executed with valid data"
        def response = restBuilder.post(resourcePath) {
            json validJson
        }
        def id = response.json.id

        then:"The response is correct"
        response.status == CREATED.value()
        response.json.id

        when: "The search action is executed to search for the instance"
        response = restBuilder.get("$resourcePath/search/grails")

        then: "The list is returned with only one instance"
        response.json.totalCount
        response.json.results
    }
}