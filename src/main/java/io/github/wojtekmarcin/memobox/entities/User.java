package io.github.wojtekmarcin.memobox.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Users")
public class User extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String login;
    private String password;

    @OneToMany(mappedBy = "userId")
    private List<MemoBox> memoBoxId;

    @OneToMany(mappedBy = "userId")
    private List<WordsSet> wordsSetId;

    public User() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public List<MemoBox> getMemoBoxId() {
        return memoBoxId;
    }

    public void setMemoBoxId(List<MemoBox> memoBoxId) {
        this.memoBoxId = memoBoxId;
    }

    public List<WordsSet> getWordsSetId() {
        return wordsSetId;
    }

    public void setWordsSetId(List<WordsSet> wordsSetId) {
        this.wordsSetId = wordsSetId;
    }
}
