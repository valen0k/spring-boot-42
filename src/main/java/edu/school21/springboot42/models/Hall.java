package edu.school21.springboot42.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "hall")
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hall_generator")
    @SequenceGenerator(name="hall_generator", sequenceName = "hall_seq", allocationSize=1)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @NonNull
    @Column(name = "seats")
    private String seats;
}
