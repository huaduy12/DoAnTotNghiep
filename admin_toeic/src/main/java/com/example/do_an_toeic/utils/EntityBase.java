package com.example.do_an_toeic.utils;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class EntityBase implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @Column(name = "ID",length = 36)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name ="CREATED_DATE", updatable = false,nullable = false,columnDefinition = "TIMESTAMP")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;

    private String createdBy;

    @Column(name ="MODIFIED_DATE", nullable = false,columnDefinition = "TIMESTAMP")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedDate;

    private String modifiedBy;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        String username = GetUser.getUserName();
        this.createdDate = now;
        this.modifiedDate = now;
        this.createdBy = username;
        System.out.println("User:" + username);
    }

    @PreUpdate
    public void preUpdate() {
        LocalDateTime now = LocalDateTime.now();
        String username = GetUser.getUserName();
        this.modifiedDate = now;
        this.modifiedBy = username;
    }

}
