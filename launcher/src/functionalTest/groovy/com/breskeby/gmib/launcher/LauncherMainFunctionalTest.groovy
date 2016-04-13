package com.breskeby.gmib.launcher

import ratpack.test.MainClassApplicationUnderTest
import spock.lang.Specification
/**
 * Created by Rene on 13/04/16.
 */
class LauncherMainFunctionalTest extends Specification {

    MainClassApplicationUnderTest aut = new MainClassApplicationUnderTest(LauncherMain)

    def "can run main app"(){
        expect:
        get("repository/org/acme/module/1.0.0/module-1.0.0.pom") == 'blubb'
        get("repository") == 'blubb'
        get("repository/org/acme/module/1.0.0/module-1.0.0.jar") == 'blubb'
    }


    private String get(String path) {
        return aut.getHttpClient().getText(path);
    }
}
