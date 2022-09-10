package ru.job4j.carsale.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
@Entity
@Table(name = "body")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Body {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String name;
}
