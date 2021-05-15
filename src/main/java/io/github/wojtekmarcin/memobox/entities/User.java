package io.github.wojtekmarcin.memobox.entities;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.*;

import static io.github.wojtekmarcin.memobox.dictionary.ValidationMessage.*;

@Entity
@Data
@Table(name = "Users", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @NotEmpty(message = EMPTY)
    @Column(unique = true)
    private String username;

    @NotEmpty(message = EMPTY)
    @Size(min = 5, message = LENGHT)
    private String password;

    private String role;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemoBox> memoBoxes;

    @OneToMany(mappedBy = "wordSetId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WordsSet> wordsSetId;

    @Embedded
    private Audit audit = new Audit();

    public User() {
    }

    public void updateFrom(final User source) {
        if (StringUtils.isNotBlank(source.getUsername())) {
            username = source.getUsername();
        }
        if (StringUtils.isNotBlank(source.getPassword())) {
            password = source.getPassword();
        }
    }

    public void addWordSet(WordsSet wordsSet) {
        wordsSetId.add(wordsSet);
        wordsSet.setUser(this);
    }

    public void removeWordSet(WordsSet wordsSet) {
        wordsSetId.remove(wordsSet);
        wordsSet.setUser(null);
    }

    public void addMemobox(MemoBox memoBox) {
        memoBoxes.add(memoBox);
        memoBox.setUser(this);
    }

    public void removeMemobox(MemoBox memoBox) {
        memoBoxes.remove(memoBox);
        memoBox.setUser(null);
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
}
