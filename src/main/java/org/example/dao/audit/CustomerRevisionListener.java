package org.example.dao.audit;

import org.example.dao.audit.model.SampleRevisionEntity;
import org.hibernate.envers.RevisionListener;

public class CustomerRevisionListener implements RevisionListener {
    @Override
    public void newRevision(Object revisionEntity) {
        SampleRevisionEntity revision = (SampleRevisionEntity) revisionEntity;
        revision.fixTimezone();
    }
}
