package io.github.wojtekmarcin.memobox.entities;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Memoboxes")
public class MemoBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memoBoxId;
    private Integer wordSlot;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "memobox_wordset",
            joinColumns = @JoinColumn(name = "memoBoxes"),
            inverseJoinColumns = @JoinColumn(name = "wordSetId"))
    private List<WordsSet> wordSetId;

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


    public List<WordsSet> getWordSetId() {
        return wordSetId;
    }

    public void setWordSetId(List<WordsSet> wordSetId) {
        this.wordSetId = wordSetId;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemoBox memoBox = (MemoBox) o;
        return memoBoxId == memoBox.memoBoxId && Objects.equals(wordSlot, memoBox.wordSlot) && Objects.equals(user, memoBox.user) && Objects.equals(wordSetId, memoBox.wordSetId) && Objects.equals(audit, memoBox.audit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memoBoxId, wordSlot, user, wordSetId, audit);
    }
}
