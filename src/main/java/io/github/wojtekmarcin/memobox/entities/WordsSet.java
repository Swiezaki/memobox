package io.github.wojtekmarcin.memobox.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

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

    @ManyToMany(mappedBy = "wordSetId")
    private Set<MemoBox> memoBoxes;

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

    public Set<MemoBox> getMemoBoxes() {
        return memoBoxes;
    }

    public void setMemoBoxes(Set<MemoBox> memoBoxes) {
        this.memoBoxes = memoBoxes;
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
