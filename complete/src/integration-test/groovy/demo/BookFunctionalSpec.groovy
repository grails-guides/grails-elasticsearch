package demo

import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import grails.testing.mixin.integration.Integration
import spock.lang.IgnoreIf
import spock.lang.Specification

@IgnoreIf( { System.getenv('TRAVIS') as boolean } )
@Integration
class BookFunctionalSpec extends Specification {

    void "Test the search action correctly searches"() {
        given:
        RestBuilder restBuilder = new RestBuilder()

        when: "The search action is executed to search for the instance"
        RestResponse response = restBuilder.get("http://localhost:$serverPort/book/search/grails")

        then: "The list is returned with only one instance"
        response.json.totalCount
        response.json.results
    }
}