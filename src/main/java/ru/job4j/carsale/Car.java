package ru.job4j.carsale;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
@Entity
@Table (name = "car")
public class Car {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String describe;
    private byte[] photo;
    @OneToMany (cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mark> marks = new ArrayList<>();
    @ManyToOne
    @JoinColumn (name = "body_id", foreignKey = @ForeignKey (name = "BODY_ID_FK"))
    private Body body;

    public Car() {
    }

    public Car(String name, String describe, byte[] photo) {
        this.name = name;
        this.describe = describe;
        this.photo = photo;
    }

    public Car(String name, String describe, byte[] photo, List<Mark> marks, Body body) {
        this.name = name;
        this.describe = describe;
        this.photo = photo;
        this.marks = marks;
        this.body = body;
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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id == car.id && Objects.equals(name, car.name) && Objects.equals(describe, car.describe) && Arrays.equals(photo, car.photo) && Objects.equals(marks, car.marks) && Objects.equals(body, car.body);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, describe, marks, body);
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Car{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", describe='").append(describe).append('\'');
        sb.append(", photo=").append(Arrays.toString(photo));
        sb.append(", marks=").append(marks);
        sb.append(", body=").append(body);
        sb.append('}');
        return sb.toString();
    }
}
