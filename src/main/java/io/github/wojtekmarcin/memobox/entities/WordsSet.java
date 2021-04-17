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
}
