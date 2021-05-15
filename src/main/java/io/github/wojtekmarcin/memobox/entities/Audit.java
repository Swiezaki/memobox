package io.github.wojtekmarcin.memobox.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Embeddable
@Data
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

    public Audit() {
    }

    public String viewCreatedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return createdOn.format(formatter);
    }

    public String viewUpdateDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return preMerge.format(formatter);
    }
}
