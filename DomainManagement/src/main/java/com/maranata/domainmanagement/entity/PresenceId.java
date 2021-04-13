package com.maranata.domainmanagement.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class PresenceId implements Serializable {

    public String nameSurname;

    public int attendingDate;
}
