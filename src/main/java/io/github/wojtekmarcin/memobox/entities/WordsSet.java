package io.github.wojtekmarcin.memobox.entities;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "WordsSets")
public class WordsSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long wordSetId;
    private String wordSetName;
    private boolean visibleFlagId;
    private boolean editionFlagId;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToMany(mappedBy = "wordSetId")
    private List<MemoBox> memoBoxes;

    @Embedded
    private Audit audit = new Audit();

    public WordsSet() {
    }

    public long getWordSetId() {
        return wordSetId;
    }

    public void setWordSetId(long wordSetId) {
        this.wordSetId = wordSetId;
    }

    public String getWordSetName() {
        return wordSetName;
    }

    public void setWordSetName(String setName) {
        this.wordSetName = setName;
    }

    public boolean isVisibleFlagId() {
        return visibleFlagId;
    }

    public void setVisibleFlagId(boolean visibleFlagId) {
        this.visibleFlagId = visibleFlagId;
    }

    public boolean isEditionFlagId() {
        return editionFlagId;
    }

    public void setEditionFlagId(boolean editionFlagId) {
        this.editionFlagId = editionFlagId;
    }

    public List<MemoBox> getMemoBoxes() {
        return memoBoxes;
    }

    public void setMemoBoxes(List<MemoBox> memoBoxes) {
        this.memoBoxes = memoBoxes;
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
        WordsSet wordsSet = (WordsSet) o;
        return wordSetId == wordsSet.wordSetId && visibleFlagId == wordsSet.visibleFlagId && editionFlagId == wordsSet.editionFlagId && Objects.equals(wordSetName, wordsSet.wordSetName) && Objects.equals(user, wordsSet.user) && Objects.equals(memoBoxes, wordsSet.memoBoxes) && Objects.equals(audit, wordsSet.audit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wordSetId, wordSetName, visibleFlagId, editionFlagId, user, memoBoxes, audit);
    }
}
