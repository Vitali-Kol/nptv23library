package org.example.model;

import java.util.UUID;

public class Autor {
    private UUID id;
    private String name;
    private String surname;

    public Autor() {
        this.id = UUID.randomUUID();
    }

    public Autor(String name, String surname) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Autor autor = (Autor) o;
        return name.equals(autor.name) && surname.equals(autor.surname);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + surname.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}