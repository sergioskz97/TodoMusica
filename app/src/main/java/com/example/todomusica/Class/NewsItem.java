package com.example.todomusica.Class;

import java.text.SimpleDateFormat;

public class NewsItem {

    String Name, Tittle, Link, DateUtc, Snippet, Thumbnail, Domain;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss'Z'");

    public NewsItem(){}

    public NewsItem(String name, String tittle, String link, String date, String snippet, String thumbnail, String domain){
        Name = name;
        Tittle = tittle;
        Link = link;
        DateUtc = date;
        Snippet = snippet;
        Thumbnail = thumbnail;
        Domain = domain;
    }

    public  String getName() { return Name; }

    public String getTittle() { return Tittle; }

    public String getLink() { return Link; }

    public String getDate() { return DateUtc; }

    public String getSnippet() { return Snippet; }

    public String getThumbnail() { return Thumbnail; }

    public String getDomain() { return Domain; }
}
