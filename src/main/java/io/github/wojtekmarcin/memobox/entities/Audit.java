package io.github.wojtekmarcin.memobox.entities;

import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Embeddable
public class Audit {
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

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getPreMerge() {
        return preMerge;
    }

    public void setPreMerge(LocalDateTime preMerge) {
        this.preMerge = preMerge;
    }

    public String viewCreatedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return createdOn.format(formatter);
    }
}
