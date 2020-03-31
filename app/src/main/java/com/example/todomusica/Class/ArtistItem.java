package com.example.todomusica.Class;

public class ArtistItem {

    Integer Id, Followers;
    String Name, Picture;

    public ArtistItem(){}

    public ArtistItem(Integer id, String name, String picture, Integer followers){
        Id = id;
        Name = name;
        Picture = picture;
        Followers = followers;
    }

    public String getName(){
        return Name;
    }

    public String getPicture() { return Picture; }

    public Integer getId() { return Id; }

    public Integer getFollowers() { return Followers; }
}
