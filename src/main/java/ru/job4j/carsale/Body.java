package ru.job4j.carsale;

import javax.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "body")
public class Body {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idserial;
    private String name;

    public Body() {
    }

    public Body(int idserial, String name) {
        this.idserial = idserial;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdserial() {
        return idserial;
    }

    public void setIdserial(int idserial) {
        this.idserial = idserial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Body body = (Body) o;
        return id == body.id && idserial == body.idserial && Objects.equals(name, body.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idserial, name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Body{");
        sb.append("id=").append(id);
        sb.append(", idserial=").append(idserial);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
