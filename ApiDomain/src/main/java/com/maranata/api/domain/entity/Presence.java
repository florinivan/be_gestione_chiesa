package com.maranata.api.domain.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "presences")
public class Presence implements Serializable {

    @EmbeddedId
    private PresenceId presenceId;

    private int childUnder14;

    private String phoneNumber;

    @Temporal( TemporalType.TIMESTAMP )
    @CreationTimestamp
    private Date createdAt;
}
