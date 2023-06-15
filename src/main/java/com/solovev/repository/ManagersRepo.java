package com.solovev.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solovev.model.SavingSubDirsManager;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Repository for reading and writing subDirs managers
 */
public class ManagersRepo {
    private Set<SavingSubDirsManager> managers = new HashSet<>();
    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Reads array of managers from file with Json format
     *
     * @param file file to read from
     * @throws IOException if an I/O error occurs
     */
    public ManagersRepo(File file) throws IOException {
        managers = objectMapper.readValue(file, new TypeReference<>() {
        });
    }

    /**
     * Adds element to the repo
     *
     * @param savingSubDirsManager manager to add to repository
     * @return true if element wa successfully added, false otherwise (element must bew unique to this repo)
     */
    public boolean add(SavingSubDirsManager savingSubDirsManager) {
        return managers.add(savingSubDirsManager);
    }

    /**
     * Saves all objects from repo to the file
     *
     * @param file to save all objects from repo
     * @throws IOException if an I/O error occurs
     */
    public void save(File file) throws IOException {
        objectMapper.writeValue(file, managers);
    }

    public Set<SavingSubDirsManager> getManagers() {
        return managers;
    }
}
