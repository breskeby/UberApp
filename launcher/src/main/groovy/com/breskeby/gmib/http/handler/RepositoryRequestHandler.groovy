package com.breskeby.gmib.http.handler

import com.breskeby.gmib.repository.mvn.MavenModule
import com.breskeby.gmib.repository.mvn.MavenFromPathParser
import ratpack.handling.Context
import ratpack.handling.Handler

public class RepositoryRequestHandler implements Handler{
    MavenFromPathParser mvnParser = new MavenFromPathParser()

    @Override
    public void handle(Context ctx) throws Exception {
        def response = ctx.getResponse()
        def path = ctx.getRequest().getPath()
        MavenModule mavenModule = mvnParser.parse(path)
        response.send("""<?xml version="1.0" encoding="UTF-8"?>
<project xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd" xmlns="http://maven.apache.org/POM/4.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <modelVersion>4.0.0</modelVersion>
  <groupId>${mavenModule.group}</groupId>
  <artifactId>${mavenModule.name}</artifactId>
  <version>${mavenModule.version}</version>
</project>""".getBytes("utf-8"));
    }
}
