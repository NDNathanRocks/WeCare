package com.example.wecare;

public class Medicine {

    String name, invCount;

    public Medicine(){

    }

    public Medicine(String name, String invCount) {
        this.name = name;
        this.invCount = invCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInvCount() {
        return invCount;
    }

    public void setInvCount(String invCount) {
        this.invCount = invCount;
    }

    public boolean isEqual(Medicine med){
        if(name.equals(med.name)){
            return true;
        }
        return false;
    }
}
