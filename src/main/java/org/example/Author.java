package org.example;

import java.util.UUID;

public class Author {
    private UUID id;
    private String authorName;
    private String authorSurname;

    public Author() {
        this.id = UUID.randomUUID();
    }
    public Author(String authorName, String authorSurname) {
        this.id = UUID.randomUUID();
        this.authorName = authorName;
        this.authorSurname = authorSurname;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", authorName='" + authorName +
        ", authorSurname='" + authorSurname +
        '}';
    }
}