package com.pquind.entity;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class Audit {

    @Column(name = "date_created")
    private LocalDateTime dateCreated;
    @Column(name = "date_modified")
    private LocalDateTime dateModified;
}
