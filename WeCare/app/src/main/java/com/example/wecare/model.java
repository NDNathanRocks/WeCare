package com.example.wecare;

public class model {

    String invCount;
    String name;

    public model() {
    }

    public model(String invCount, String name) {
        this.invCount = invCount;
        this.name = name;
//        this.purl = purl;
    }

    public String getInvCount() {
        return invCount;
    }

    public void setInvCount(String invCount) {
        this.invCount = invCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getPurl() {
//        return purl;
//    }
//
//    public void setPurl(String purl) {
//        this.purl = purl;
//    }
}

