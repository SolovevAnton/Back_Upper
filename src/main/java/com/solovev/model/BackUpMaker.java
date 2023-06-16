package com.solovev.model;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Consumer;

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
     * Note when this method is called, dirsManager updates its backUp Queue;
     * Last backUp is put to the folder of the last_removedBackUp
     */
    public void doBackUp() throws IOException {
        //move the backUp to replace to the last removed folder
        moveLastBackUp();

        //updates manager and gets new sub dir to save fresh backUp
        File savingSubDirectory = savingSubDirectory();
        createOrClean(savingSubDirectory);

        //Copying depend on if it is file or dir
        if (Files.isDirectory(config.getTargetDir())) {
            FileUtils.copyDirectoryToDirectory(config.getTargetDir().toFile(), savingSubDirectory);
        } else {
            FileUtils.copyFileToDirectory(config.getTargetDir().toFile(), savingSubDirectory);
        }
    }

    /**
     * Moves back up to be replaced (if any) to the specified folder in the manager
     */

    private void moveLastBackUp() throws IOException {

        Optional<Path> backUpToReplace = savingDirsManager.peekPathToReplace();

        if (backUpToReplace.isPresent()) {

            File directoryToStoreLastBackUp = config.getRootDirToStoreBackUps()
                    .resolve(savingDirsManager.getDirNameForLastReplacedBackUp())
                    .toAbsolutePath()
                    .toFile();

            createOrClean(directoryToStoreLastBackUp);

            FileUtils.moveDirectoryToDirectory(backUpToReplace.get().toFile(),
                    directoryToStoreLastBackUp,
                    false);
        }
    }

    /**
     * Creates directory is it does not exist, if it exists cleans it
     *
     * @param directory to clean or create
     */
    private void createOrClean(File directory) throws IOException {
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
