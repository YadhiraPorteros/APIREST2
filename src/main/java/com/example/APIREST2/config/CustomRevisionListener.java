package com.example.APIREST2.config;

import com.example.APIREST2.entities.audit.Revision;
import org.hibernate.envers.RevisionListener;

public class CustomRevisionListener implements RevisionListener {
    @Override
    public void newRevision(Object revisionEntity) {
        final Revision revision = (Revision) revisionEntity;

    }
}
