package org.example;

public class item {
    private int ID;
    private String code_;
    private String name_;

    public item(int id, String code_, String name_) {
        this.ID = id;
        this.code_ = code_;
        this.name_ = name_;

    }

    public item() {

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
    @Override
    public String toString() {
        return "Item[ID=" + ID + ", Code=" + code_ + ", Name=" + name_ + "]";
    }
}
