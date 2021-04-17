package io.github.wojtekmarcin.memobox.entities;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Memoboxes")
public class MemoBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memoBoxId;
    private Integer wordSlot;

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
}
