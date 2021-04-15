package io.github.wojtekmarcin.memobox.entities;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Users", uniqueConstraints = {@UniqueConstraint(columnNames = "login")})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @NotEmpty(message = "Field cannot be empty")
    @Column(unique = true)
    private String login;

    @NotEmpty(message = "Field cannot be empty")
    @Size(min = 5, message = "Password should be longer")
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "memoBoxId")
    private List<MemoBox> memoBoxes;

    @OneToMany(mappedBy = "userId")
    private List<WordsSet> wordsSetId;

    @Embedded
    private Audit audit = new Audit();

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

    public List<WordsSet> getWordsSetId() {
        return wordsSetId;
    }

    public void setWordsSetId(List<WordsSet> wordsSetId) {
        this.wordsSetId = wordsSetId;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    public List<MemoBox> getMemoBoxes() {
        return memoBoxes;
    }

    public void setMemoBoxes(List<MemoBox> memoBoxes) {
        this.memoBoxes = memoBoxes;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("userId=").append(userId);
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", memoBoxes=").append(memoBoxes);
        sb.append(", wordsSetId=").append(wordsSetId);
        sb.append(", audit=").append(audit);
        sb.append('}');
        return sb.toString();
    }
}
