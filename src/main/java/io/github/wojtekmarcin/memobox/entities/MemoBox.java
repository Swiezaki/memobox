package io.github.wojtekmarcin.memobox.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
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
}
