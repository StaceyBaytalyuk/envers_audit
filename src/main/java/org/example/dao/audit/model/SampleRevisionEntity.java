package org.example.dao.audit.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.RevisionEntity;

@Entity
@RevisionEntity
@ToString
@NoArgsConstructor
@Table(schema = "structural", name = "revision_info")
public class SampleRevisionEntity extends CustomRevisionEntity {
}
