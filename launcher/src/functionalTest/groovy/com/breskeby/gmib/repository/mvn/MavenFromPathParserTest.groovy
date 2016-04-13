package com.breskeby.gmib.repository.mvn

import spock.lang.Specification

class MavenFromPathParserTest extends Specification {

    def 'can create maven module from path'() {
        when:
        def module = new MavenFromPathParser().parse(path)

        then:
        module.version == version
        module.group == group
        module.name == name

        where:
        path                                          | group           | name      | version
        'org/acme/module/1.0.0/module-1.0.0.pom'      | 'org.acme'      | 'module'  | '1.0.0'
        'org/acme/core/module/1.0.0/module-1.0.0.pom' | 'org.acme.core' | 'module'  | '1.0.0'
        'org/acme/module2/1.0.0/module2-1.0.0.pom'    | 'org.acme'      | 'module2' | '1.0.0'
        'org/acme/module2/2.0.0/module2-2.0.0.pom'    | 'org.acme'      | 'module2' | '2.0.0'
        'org/acme/module/2.0.0/module-2.0.0.pom'      | 'org.acme'      | 'module'  | '2.0.0'
    }
}
