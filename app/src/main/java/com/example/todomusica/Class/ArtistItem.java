package com.example.todomusica.Class;

import org.json.JSONArray;
import org.json.JSONException;

public class ArtistItem {

    Integer Id, Followers;
    String Name, Picture, Genre;

    public ArtistItem(){}

    public ArtistItem(Integer id, String name, String picture, Integer followers, String genre){
        Id = id;
        Name = name;
        Picture = picture;
        Followers = followers;
        Genre = genre;
    }

    public String getName(){
        return Name;
    }

    public String getPicture() { return Picture; }

    public Integer getId() { return Id; }

    public Integer getFollowers() { return Followers; }

    public String getGenre() { return Genre; }

    public String getGenre(int pos) {
        String aux = "Genero";

        try {
            JSONArray jsonArray = new JSONArray(Genre);
            aux = jsonArray.getString(pos).toUpperCase();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return aux;
    }
}
