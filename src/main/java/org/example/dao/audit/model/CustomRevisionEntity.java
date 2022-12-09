package org.example.dao.audit.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomRevisionEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @RevisionNumber
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "revision_number")
    private Long number;

    @RevisionTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "revision_timestamp")
    private Date timestamp;
}
