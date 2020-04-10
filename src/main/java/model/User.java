package model;

import com.google.gson.Gson;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "passport")
    private long passport;

    public User() {
    }

    public User(String name, int age, long passport) {
        this.name = name;
        this.age = age;
        this.passport = passport;
    }
    public User(long id, String name, int age, long passport) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.passport = passport;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getPassport() {
        return passport;
    }

    public void setPassport(long passport) {
        this.passport = passport;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", passport=" + passport +
                '}';
    }

    public String toGson(){
        return new Gson().toJson(this);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                passport == user.passport &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, passport);
    }
}
