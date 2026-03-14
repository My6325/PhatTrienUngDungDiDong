package com.example.vnedudluctk472312693;

public class Category {
    int cat_id; //danh mục
    String name; //Lương, ăn uống
    int type; //thu/chi
    public Category(){

    }
    public Category(String name, int type){
        this.name=name;
        this.type=type;
    }
    public int getCat_ID(){
        return cat_id;
    }
    public void setCat_id(int cat_id){
        this.cat_id=cat_id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public int getType(){
        return cat_id;
    }
    public void setType(int type){
        this.type=type;
    }
    @Override
    public String toString() {
        return name; // Để Spinner hiện tên danh mục
    }
}
