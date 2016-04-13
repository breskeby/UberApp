package com.breskeby.gmib.repository.mvn

class MavenModule {
    String group
    String version
    String name

    public MavenModule(String group, String name, String version){
        this.name = name
        this.version = version
        this.group = group
    }
}
