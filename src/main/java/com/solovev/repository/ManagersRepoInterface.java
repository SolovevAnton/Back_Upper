package com.solovev.repository;

import com.solovev.model.SavingSubDirsManager;

import java.io.IOException;
import java.util.Collection;

/**
 * Interface to work with repositories with managers repo
 */
public interface ManagersRepoInterface {
    Collection<SavingSubDirsManager> getData();
    void save() throws IOException;
}
