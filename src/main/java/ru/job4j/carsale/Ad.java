package ru.job4j.carsale;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
@Table(name = "ad")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    private boolean isSold;
    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDate date;
    @OneToOne(fetch = FetchType.LAZY)
    private Car car;
    @ManyToOne
    @JoinColumn (name = "author_id", foreignKey = @ForeignKey (name = "MARK_ID_FK"))
    private Author author;

    public Ad() {
    }

    public Ad(String content, boolean isSold, LocalDate date, Car car, Author author) {
        this.content = content;
        this.isSold = isSold;
        this.date = date;
        this.car = car;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ad ad = (Ad) o;
        return id == ad.id && isSold == ad.isSold && Objects.equals(content, ad.content) && Objects.equals(author, ad.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, isSold, author);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ad{");
        sb.append("id=").append(id);
        sb.append(", content='").append(content).append('\'');
        sb.append(", isSold=").append(isSold);
        sb.append(", author=").append(author);
        sb.append('}');
        return sb.toString();
    }
}
