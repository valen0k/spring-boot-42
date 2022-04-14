package edu.school21.springboot42.models;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "film_generator")
    @SequenceGenerator(name="film_generator", sequenceName = "film_seq", allocationSize=1)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "year")
    private String year;

    @Column(name = "agelimit")
    private String ageLimit;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "image")
    private String image;

    public Film(String title, String year, String ageLimit, String description, String image) {
        this.title = title;
        this.year = year;
        this.ageLimit = ageLimit;
        this.description = description;
        this.image = image;
    }

}
