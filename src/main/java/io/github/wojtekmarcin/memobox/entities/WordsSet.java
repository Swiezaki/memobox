package io.github.wojtekmarcin.memobox.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "WordsSets")
public class WordsSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long wordSetId;
    private String setName;
    private boolean visibleFlagId;
    private boolean editionFlagId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User userId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "wordsSetWordId")
    private Word wordId;

    @ManyToMany(mappedBy = "wordSetId")
    private List<MemoBox> memoBoxSetId;

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

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
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

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Word getWordId() {
        return wordId;
    }

    public void setWordId(Word wordId) {
        this.wordId = wordId;
    }

    public List<MemoBox> getMemoBoxSetId() {
        return memoBoxSetId;
    }

    public void setMemoBoxSetId(List<MemoBox> memoBoxSetId) {
        this.memoBoxSetId = memoBoxSetId;
    }
}
