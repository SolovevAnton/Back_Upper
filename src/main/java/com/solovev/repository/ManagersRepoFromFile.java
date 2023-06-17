package com.solovev.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solovev.model.SavingSubDirsManager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Repository for reading and writing subDirs managers
 */
public class ManagersRepoFromFile implements ManagersRepoInterface {
    private Set<SavingSubDirsManager> managers = new HashSet<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Path pathToSFileToSaveState;

    /**
     * Reads array of managers from path with Json format
     *
     * @param path path to read from
     * @throws IOException if an I/O error occurs
     */
    public ManagersRepoFromFile(Path path) throws IOException {
        pathToSFileToSaveState = path.toAbsolutePath();
        //check if file is empty
        if (path.toFile().length() > 0) {
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
     * Saves all objects from repo to the folder with the path that was used to create repo
     *
     * @throws IOException if an I/O error occurs
     */
    public void save() throws IOException {

        objectMapper.writeValue(pathToSFileToSaveState.toFile(), managers);
    }

    @Override
    public Collection<SavingSubDirsManager> getData() {
        return managers;
    }
}

