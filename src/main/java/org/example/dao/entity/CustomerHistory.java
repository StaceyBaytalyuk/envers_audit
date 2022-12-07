package org.example.dao.entity;

import org.example.dao.audit.model.CustomRevisionEntity;
import org.hibernate.envers.RevisionType;

import java.util.UUID;

//@Table(name = "customer_aud", schema = "structural") // необязательно писать
public record CustomerHistory(
        UUID customerId,
        String customerName, // и другие поля, чьи изменения нужно отобразить
        CustomRevisionEntity revisionInfo,
        RevisionType revisionType) {
}
