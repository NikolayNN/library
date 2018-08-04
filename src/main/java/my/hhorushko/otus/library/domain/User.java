package my.hhorushko.otus.library.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank
    @Column(unique = true)
    private String name;

    public User(@NotBlank String name) {
        this.name = name;
    }

    public User() {
    }
}
