package com.example.todomusica.Class;

public class NewsItem {

    String Name, Tittle, Link, Date, Snippet, Thumbnail, Domain;

    public NewsItem(){}

    public NewsItem(String name, String tittle, String link, String date, String snippet, String thumbnail, String domain){
        Name = name;
        Tittle = tittle;
        Link = link;
        Date = date;
        Snippet = snippet;
        Thumbnail = thumbnail;
        Domain = domain;
    }

    public  String getName() { return Name; }

    public String getTittle() { return Tittle; }

    public String getLink() { return Link; }

    public String getDate() { return Date; }

    public String getSnippet() { return Snippet; }

    public String getThumbnail() { return Thumbnail; }

    public String getDomain() { return Domain; }
}
