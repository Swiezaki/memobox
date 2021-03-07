package io.github.wojtekmarcin.memobox.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;
    private String login;
    private String password;

    @OneToMany(mappedBy = "user_id")
    private List<MemoBox> memoBox_id;

    public User() {
    }
}
