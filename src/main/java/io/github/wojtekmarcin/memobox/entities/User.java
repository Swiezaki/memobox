package io.github.wojtekmarcin.memobox.entities;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Users", uniqueConstraints = {@UniqueConstraint(columnNames = "login")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    @NotNull
    @Column(unique = true)
    private String login;
    @NotNull
    private String password;

    @OneToMany(mappedBy = "userId")
    private List<MemoBox> memoBoxId;

    @OneToMany(mappedBy = "userId")
    private List<WordsSet> wordsSetId;

    @Embedded
    private Audit audit = new Audit();

    public User() {
    }

    public long getUserId() {
        return userId;
    }

    void setUserId(long userId) {
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

    public void updateFrom(final User source) {
        if (StringUtils.isNotBlank(source.getLogin())) {
            login = source.getLogin();
        }
        if (StringUtils.isNotBlank(source.getPassword())) {
            password = source.getPassword();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password);
    }
}
