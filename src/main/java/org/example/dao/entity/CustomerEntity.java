package org.example.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Entity
@Audited
@EntityListeners({AuditingEntityListener.class})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer", schema = "structural")
public class CustomerEntity {
    @Id
    @Column(name = "customer_id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city;

    @NotAudited
    @Column(name = "age")
    private String age;
}
