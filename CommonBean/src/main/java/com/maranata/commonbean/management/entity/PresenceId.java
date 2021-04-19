package com.maranata.commonbean.management.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class PresenceId implements Serializable {

    public String nameSurname;

    public int attendingDate;
}
