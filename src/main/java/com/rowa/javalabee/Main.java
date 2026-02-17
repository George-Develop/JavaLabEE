package com.rowa.javalabee;

import org.apache.catalina.startup.Tomcat;

public class Main {
    public static void main(String[] args) throws Exception {
        String webappDir = "src/main/webapp";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.addWebapp("", new java.io.File(webappDir).getAbsolutePath());
        tomcat.start();
        tomcat.getServer().await();
    }
}