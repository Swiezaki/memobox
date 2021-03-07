package io.github.wojtekmarcin.memobox.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;
    private String login;
    private String password;

    @OneToMany(mappedBy = "user_id")
    private List<MemoBox> memoBox_id;

    @OneToMany(mappedBy = "user_id")
    private List<WordsSet> wordsSet_id;

    public User() {
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<MemoBox> getMemoBox_id() {
        return memoBox_id;
    }

    public void setMemoBox_id(List<MemoBox> memoBox_id) {
        this.memoBox_id = memoBox_id;
    }

    public List<WordsSet> getWordsSet_id() {
        return wordsSet_id;
    }

    public void setWordsSet_id(List<WordsSet> wordsSet_id) {
        this.wordsSet_id = wordsSet_id;
    }
}
