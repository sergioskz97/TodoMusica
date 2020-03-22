package com.example.todomusica.Class;

public class ArtistItem {

    int Id;
    String Name, Picture;

    public ArtistItem(){}

    public ArtistItem(int id, String name, String picture){
        Id = id;
        Name = name;
        Picture = picture;
    }

    public String getName(){
        return Name;
    }
}
