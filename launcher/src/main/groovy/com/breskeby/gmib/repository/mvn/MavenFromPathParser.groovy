package com.breskeby.gmib.repository.mvn

class MavenFromPathParser {
    MavenModule parse(String path) {
        def filename = path.substring(path.lastIndexOf("/") + 1)
        def moduleName = filename.substring(0, filename.indexOf("-"))
        def groupName = path.substring(0, path.lastIndexOf("/${moduleName}/")).replace('/', '.');
        def versionString = path.substring(path.lastIndexOf("/${moduleName}/") + moduleName.length()+2, path.lastIndexOf("/${moduleName}-"))
        return new MavenModule(groupName, moduleName, versionString)
    }
}
