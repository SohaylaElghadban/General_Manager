package org.example;

public class Store {
    private int ID;
    private String code_;
    private String name_;


    public Store(int id ,String storeCode, String name_) {
        this.ID = id;
        this.code_ = storeCode;
        this.name_ = name_;
    }

    public Store() {

    }

    public String getCode_() {
        return code_;
    }

    public void setCode_(String code_) {
        this.code_ = code_;
    }

    public String getName_() {
        return name_;
    }

    public void setName_(String name_) {
        this.name_ = name_;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
