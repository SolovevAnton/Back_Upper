package com.solovev.model;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class BackUpMaker {
    private final Configuration config;
    private final SavingSubDirsManager savingDirsManager;

    public BackUpMaker(SavingSubDirsManager savingSubDirsManager) {
        this.config = savingSubDirsManager.getConfig();
        savingDirsManager = savingSubDirsManager;

    }
    //toDo add last replaced backUp

    /**
     * Creates backUp folder, if not created, and saves all files with dirs from paths to it
     * Note when this method is called, dirsManager updates its backUp Queue
     */
    public void doBackUp() throws IOException {
        File savingSubDirectory = savingSubDirectory();
        createAndClean(savingSubDirectory);

        //save depend on if it is file or dir
        if (Files.isDirectory(config.getTargetDir())) {
            FileUtils.copyDirectory(config.getTargetDir().toFile(), savingSubDirectory);
        } else {
            FileUtils.copyFileToDirectory(config.getTargetDir().toFile(), savingSubDirectory);
        }
    }

    /**
     * Creates directory is it does not exist, if it exists cleans it
     *
     * @param directory to clean or create
     */
    private void createAndClean(File directory) throws IOException {
        if (!directory.mkdirs()) {
            FileUtils.cleanDirectory(directory);
        }
    }

    /**
     * Identifies the Path to the directory to save file into
     *
     * @return path to the subdir to save back_ups
     */
    private File savingSubDirectory() {
        return savingDirsManager.getBackUpDir().toFile();
    }

    @Override
    public String toString() {
        return "BackUpMaker{" +
                "config=" + config +
                '}';
    }
}
