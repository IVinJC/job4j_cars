package ru.job4j.carsale.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ad")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String name;
    @NonNull
    private byte[] photo;
    @NonNull
    private String description;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "mark_id", referencedColumnName = "id")
    private Mark mark;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "model_id", referencedColumnName = "id")
    private Model model;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "body_id", referencedColumnName = "id")
    private Body body;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;
    @NonNull
    private int price;
    @NonNull
    private LocalDateTime created;
    private String formattedDate;
    @NonNull
    private boolean status;
}
