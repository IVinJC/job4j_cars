package ru.job4j.carsale.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "model")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String name;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "mark_id", referencedColumnName = "id")
    private Mark mark;
}
