/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.utilities;

import java.net.InetAddress;
import java.util.Objects;

/**
 *
 * @author Mac
 */
public class ConcreteResourceURI implements ResourceURI {

    private static final String SEPARATOR = "/";
    private InetAddress base;
    private String path;
    private boolean isBuilt;
    private StringBuilder pathBuilder;

    public ConcreteResourceURI() {
        isBuilt = false;
    }

    @Override
    public InetAddress getBase() {
        return base;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public ResourceURI base(InetAddress ip) {
        this.base = ip;

        return ConcreteResourceURI.this;
    }

    @Override
    public ResourceURI path(String path) {
        if (Objects.nonNull(path)) {
            if (Objects.isNull(this.pathBuilder)) {
                this.pathBuilder = new StringBuilder(path);
            } else {
                this.pathBuilder.append(SEPARATOR);
                this.pathBuilder.append(path);
            }
        }
        return ConcreteResourceURI.this;
    }

    @Override
    public String getUriWithHttp() throws Exception {
        if (!isBuilt) {
            build();
        }
        return "http:" + SEPARATOR + SEPARATOR + base.getHostAddress() + SEPARATOR + path;
    }

    @Override
    public String getUriWithoutHttp() throws Exception {
        if (!isBuilt) {
            build();
        }
        return base.getHostAddress() + SEPARATOR + path;
    }

    @Override
    public ResourceURI build() throws Exception {
        if (Objects.isNull(base) || Objects.isNull(pathBuilder)) {
            throw new Exception("Invalid URI");
        }
        path = pathBuilder.toString();
        pathBuilder = null;
        isBuilt = true;
        return ConcreteResourceURI.this;
    }

    @Override
    public ResourceURI clone() {
        ResourceURI uri = new ConcreteResourceURI();
        uri.base(this.getBase());
        uri.path(this.getPath());
        return uri;
    }

    @Override
    public boolean isBuilt() {
        return isBuilt;
    }
}
