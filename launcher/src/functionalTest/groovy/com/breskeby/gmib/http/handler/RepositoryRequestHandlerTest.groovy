package com.breskeby.gmib.http.handler

import org.junit.Rule
import org.junit.rules.TemporaryFolder
import ratpack.handling.Context
import ratpack.http.Request
import ratpack.http.Response
import spock.lang.Specification

/**
 * Created by Rene on 13/04/16.
 */
class RepositoryRequestHandlerTest extends Specification {

    RepositoryRequestHandler handler = new RepositoryRequestHandler()

    @Rule
    TemporaryFolder temporaryFolder

    Context contextMock = Mock()
    Request requestMock = Mock()
    Response responseMock = Mock()

    def "response pom requests"() {
        when:
        handler.handle(context(path: path))
        then:
        1 * responseMock.send(pom(group, module, version).getBytes('utf-8'))

        where:
        path                                     | group      | module   | version
        'org/acme/module/1.0.0/module-1.0.0.pom' | 'org.acme' | 'module' | '1.0.0'
        'org/acme/module2/2.0.0/module-2.0.0.pom' | 'org.acme' | 'module' | '2.0.0'
    }

    static String pom(String group, String name, String version) {
        return """<?xml version="1.0" encoding="UTF-8"?>
<project xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd" xmlns="http://maven.apache.org/POM/4.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <modelVersion>4.0.0</modelVersion>
  <groupId>$group</groupId>
  <artifactId>$name</artifactId>
  <version>$version</version>
</project>""".toString()
    }

    Context context(Map params) {
        _ * requestMock.getPath() >> params.path
        _ * contextMock.getRequest() >> requestMock
        _ * contextMock.getResponse() >> responseMock
        contextMock
    }
}
