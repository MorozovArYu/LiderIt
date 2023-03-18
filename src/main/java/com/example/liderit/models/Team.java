package com.example.liderit.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "sport_kind")
    private String sportKind;
    @Column(name = "creation_date")
    private LocalDate creationDate;

    public Team(String name, String sportKind, LocalDate creationDate) {
        this.name = name;
        this.sportKind = sportKind;
        this.creationDate = creationDate;
    }

    public Team() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSportKind() {
        return sportKind;
    }

    public void setSportKind(String sportKind) {
        this.sportKind = sportKind;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id == team.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sportKind='" + sportKind + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }


}
