package com.solovev.repository;

import com.solovev.model.Configuration;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class FilesRepo {
    private final Configuration config;
    private Set<Path> paths = new HashSet<>();

    public FilesRepo(Configuration config) throws IOException {
        this.config = config;
        try(Stream<Path> walker = Files.walk(config.getPathToFiles())){
            //relatives paths to rootDirectoryToCopy. If directory matches absolute root
            Path rootToRemove = config.getPathToFiles().getParent();
            if(rootToRemove == null) {
                throw new IOException("Directory to copy cannot be absolute root");
            }

            walker
                    .map(rootToRemove :: relativize)
                    .forEach(paths:: add);
        }
    }


    /**
     * Creates backUp folder, if not created, and saves all files with dirs from paths to it
     */
    public void doBackUp() throws IOException {

    }

    /**
     * Identifies the Path to the directory to save file into
     * @return path to the subdir to save back_ups
     */
    private Path savingSubDirectory(){
        Path backUpSubDir = Path.of(config.getBackUpSubDirs());
        return config.getRootDirToStoreBackUps().resolve(backUpSubDir);
    }
    /**
     * Method clear folder from all files and dir and delete it.
     */
    private boolean clearDir(Path path){

    }


    /**
     * Makes all not presented dirs from paths
     */
    private void makeDirs(){
        paths.stream()
                .filter(Files :: isDirectory)
                .map(Path::toFile)
                .forEach(File::mkdir);
    }
    @Override
    public String toString() {
        return "FilesRepo{" +
                "config=" + config +
                ",\npaths=" + paths +
                '}';
    }
}
