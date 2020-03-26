package com.example.todomusica.Class;

public class ArtistItem {

    Integer Id;
    String Name, Picture;

    public ArtistItem(){}

    public ArtistItem(Integer id, String name, String picture){
        Id = id;
        Name = name;
        Picture = picture;
    }

    public String getName(){
        return Name;
    }

    public String getPicture() { return Picture; }

    public Integer getId() { return Id; }
}
