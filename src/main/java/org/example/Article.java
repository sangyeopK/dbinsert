package org.example;

public class Article {
    private final String content;
    private final String title;
    private final int id;

    Article(int id, String title, String content){
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }
}
