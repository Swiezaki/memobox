package io.github.wojtekmarcin.memobox.entities;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
abstract class Audit {
    private LocalDateTime createdOn;
    private LocalDateTime preMerge;

    @PrePersist
    void prePersist() {
        createdOn = LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate() {
        preMerge = LocalDateTime.now();
    }
}
