package my.hhorushko.otus.library.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String name;
}
