package io.github.wojtekmarcin.memobox.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
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
}
