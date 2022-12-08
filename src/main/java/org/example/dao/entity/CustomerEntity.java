package org.example.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
