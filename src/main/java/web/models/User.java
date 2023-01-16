package web.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")

public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@GenericGenerator(name = "increment", strategy = "increment")
private long id;
@Column(name = "name")
private String name;
@Column(name = "surname")
private String surName;
@Column(name = "age")
private byte age;

public User(long id, String name, String surName, byte age) {
    this.id = id;
    this.name = name;
    this.surName = surName;
    this.age = age;
}

public User() {
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

public String getSurName() {
    return surName;
}

public void setSurName(String surName) {
    this.surName = surName;
}

public byte getAge() {
    return age;
}

public void setAge(byte age) {
    this.age = age;
}

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return id == user.id && age == user.age && Objects.equals(name, user.name) && Objects.equals(surName, user.surName);
}

@Override
public int hashCode() {
    return Objects.hash(id, name, surName, age);
}

@Override
public String toString() {
    return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", surName='" + surName + '\'' +
            ", age=" + age +
            '}';
}
}
