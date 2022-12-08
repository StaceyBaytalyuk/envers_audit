package org.example.dao.audit.model;

import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.dao.audit.CustomerRevisionListener;
import org.hibernate.envers.RevisionEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@RevisionEntity(CustomerRevisionListener.class)
@ToString
@NoArgsConstructor
@Table(schema = "structural", name = "revision_info")
public class SampleRevisionEntity extends CustomRevisionEntity {
}
