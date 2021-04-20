package io.github.wojtekmarcin.memobox.entities;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Users", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @NotEmpty(message = "Field cannot be empty")
    @Column(unique = true)
    private String username;

    @NotEmpty(message = "Field cannot be empty")
    @Size(min = 5, message = "Password should be longer")
    private String password;

    private String role;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "memoBoxId")
    private List<MemoBox> memoBoxes;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "wordSetId")
    private List<WordsSet> wordsSetId;

    @Embedded
    private Audit audit = new Audit();

    public User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void updateFrom(final User source) {
        if (StringUtils.isNotBlank(source.getUsername())) {
            username = source.getUsername();
        }
        if (StringUtils.isNotBlank(source.getPassword())) {
            password = source.getPassword();
        }
    }

    public long getUserId() {
        return userId;
    }

    public String getRole() {
        return role;
    }

    public List<MemoBox> getMemoBoxes() {
        return memoBoxes;
    }

    public List<WordsSet> getWordsSetId() {
        return wordsSetId;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setMemoBoxes(List<MemoBox> memoBoxes) {
        this.memoBoxes = memoBoxes;
    }

    public void setWordsSetId(List<WordsSet> wordsSetId) {
        this.wordsSetId = wordsSetId;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(role, user.role) && Objects.equals(memoBoxes, user.memoBoxes) && Objects.equals(wordsSetId, user.wordsSetId) && Objects.equals(audit, user.audit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, role, memoBoxes, wordsSetId, audit);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("userId=").append(userId);
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", role='").append(role).append('\'');
        sb.append(", memoBoxes=").append(memoBoxes);
        sb.append(", wordsSetId=").append(wordsSetId);
        sb.append(", audit=").append(audit);
        sb.append('}');
        return sb.toString();
    }
}
