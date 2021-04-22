package io.github.wojtekmarcin.memobox.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Memoboxes")
public class MemoBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memoBoxId;
    private String memoboxName;
    private Integer wordSlot;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "memobox_wordset",
            joinColumns = @JoinColumn(name = "memoBoxes"),
            inverseJoinColumns = @JoinColumn(name = "wordSetId"))
    private Set<WordsSet> wordSetId;

    @Embedded
    private Audit audit = new Audit();

    public MemoBox() {
    }

    public long getMemoBoxId() {
        return memoBoxId;
    }

    public void setMemoBoxId(long memoBoxId) {
        this.memoBoxId = memoBoxId;
    }

    public Integer getWordSlot() {
        return wordSlot;
    }

    public void setWordSlot(Integer wordSlot) {
        this.wordSlot = wordSlot;
    }

    public Set<WordsSet> getWordSetId() {
        return wordSetId;
    }

    public void setWordSetId(Set<WordsSet> wordSetId) {
        this.wordSetId = wordSetId;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMemoboxName() {
        return memoboxName;
    }

    public void setMemoboxName(String memoboxName) {
        this.memoboxName = memoboxName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemoBox memoBox = (MemoBox) o;
        return memoBoxId == memoBox.memoBoxId && Objects.equals(memoboxName, memoBox.memoboxName) && Objects.equals(wordSlot, memoBox.wordSlot) && Objects.equals(user, memoBox.user) && Objects.equals(wordSetId, memoBox.wordSetId) && Objects.equals(audit, memoBox.audit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memoBoxId, memoboxName, wordSlot, user, wordSetId, audit);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MemoBox{");
        sb.append("memoBoxId=").append(memoBoxId);
        sb.append(", memoboxName='").append(memoboxName).append('\'');
        sb.append(", wordSlot=").append(wordSlot);
        sb.append(", user=").append(user);
        sb.append(", wordSetId=").append(wordSetId);
        sb.append(", audit=").append(audit);
        sb.append('}');
        return sb.toString();
    }
}
