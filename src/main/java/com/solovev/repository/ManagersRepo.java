package com.solovev.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solovev.model.SavingSubDirsManager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
 * Repository for reading and writing subDirs managers
 */
public class ManagersRepo {
    private Set<SavingSubDirsManager> managers = new HashSet<>();
    ObjectMapper objectMapper = new ObjectMapper();

    public ManagersRepo() {
    }

    /**
     * Reads array of managers from path with Json format
     *
     * @param path path to read from
     * @throws IOException if an I/O error occurs
     */
    public ManagersRepo(Path path) throws IOException {
        //check if file is empty
        if(path.toFile().length() > 0) {
            managers = objectMapper.readValue(path.toFile(), new TypeReference<>() {
            });
        }
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
     * Saves all objects from repo to the folder with the path named : save
     *
     * @param path to save all objects from repo
     * @throws IOException if an I/O error occurs
     */
    public void save(Path path) throws IOException {
        objectMapper.writeValue(path.toFile(), managers);
    }

    public Set<SavingSubDirsManager> getManagers() {
        return managers;
    }
}
