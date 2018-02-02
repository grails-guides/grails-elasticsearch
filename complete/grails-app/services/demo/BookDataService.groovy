package demo

import grails.gorm.services.Service
import groovy.transform.CompileStatic

@CompileStatic
@Service(Book)
interface BookDataService {
    Book save(String name, String about)
}