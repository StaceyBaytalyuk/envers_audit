package org.example.dao.audit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    @GeneratedValue
    @Column(name = "revision_number")
    // соответствует полю rev в таблице customer_aud. rev нельзя переименовывать, иначе не работает Envers
    private Long number;

    @RevisionTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "revision_timestamp")
    private Date timestamp; // todo LocalDateTime: since hibernate 6.0.0.Beta2

    private static final ZoneId URK_ZONE = ZoneId.of("Europe/Kiev");

    public void fixTimezone() {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(timestamp.toInstant(), URK_ZONE);
        timestamp = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public LocalDateTime getRevisionDate() {
        return LocalDateTime.ofInstant(timestamp.toInstant(), ZoneId.systemDefault());
    }
}
