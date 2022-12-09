package org.example.dao.audit.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@RevisionEntity
@Table(schema = "structural", name = "revision_info")
public class CustomerRevisionEntity implements Serializable {
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
    private LocalDateTime timestamp;
}
