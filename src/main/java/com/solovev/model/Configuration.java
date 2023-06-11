package com.solovev.model;

import java.nio.file.Path;
import java.util.Objects;

/**
 * Class to store config for every folder that needs to be backUpped
 */
public class Configuration {
    private static final Path DIR_LAST_REPLACED_BACKUP = Path.of("last_replaced_backup");
    private final Path pathToFiles;
    private Path rootDirToStoreBackUps = Path.of(System.getProperty("user.dir")).toAbsolutePath();
    private int numberOfBackups = 1; // default is 1
    private String backUpSubDirs = "Back_up";
    private Path backUpToReplace;

    public Configuration() {
        this.pathToFiles = Path.of("").toAbsolutePath(); // default will not work
    }

    public Configuration(Path pathToBackUp) {
        this.pathToFiles = pathToBackUp.toAbsolutePath();
    }

    public Configuration(Path pathToBackUp, Path rootDirToSave, int numberOfBackups, String backUpDirName, Path backUpToReplace) {
        this.pathToFiles = pathToBackUp.toAbsolutePath();
        this.rootDirToStoreBackUps = rootDirToSave.toAbsolutePath();
        this.numberOfBackups = numberOfBackups;
        this.backUpSubDirs = backUpDirName;
        this.backUpToReplace = backUpToReplace;
    }

    public Path getPathToFiles() {
        return pathToFiles;
    }

    public Path getRootDirToStoreBackUps() {
        return rootDirToStoreBackUps;
    }

    public void setRootDirToStoreBackUps(Path rootDirToStoreBackUps) {
        this.rootDirToStoreBackUps = rootDirToStoreBackUps.toAbsolutePath();
    }

    public int getNumberOfBackups() {
        return numberOfBackups;
    }

    public void setNumberOfBackups(int numberOfBackups) {
        this.numberOfBackups = numberOfBackups;
    }

    public String getBackUpSubDirs() {
        return backUpSubDirs;
    }

    public void setBackUpSubDirs(String backUpSubDirs) {
        this.backUpSubDirs = backUpSubDirs;
    }

    public Path getBackUpToReplace() {
        return backUpToReplace;
    }

    public void setBackUpToReplace(Path backUpToReplace) {
        this.backUpToReplace = backUpToReplace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Configuration configuration = (Configuration) o;

        if (numberOfBackups != configuration.numberOfBackups) return false;
        if (!Objects.equals(pathToFiles, configuration.pathToFiles)) return false;
        if (!Objects.equals(rootDirToStoreBackUps, configuration.rootDirToStoreBackUps))
            return false;
        if (!Objects.equals(backUpSubDirs, configuration.backUpSubDirs))
            return false;
        return Objects.equals(backUpToReplace, configuration.backUpToReplace);
    }

    @Override
    public int hashCode() {
        int result = pathToFiles != null ? pathToFiles.hashCode() : 0;
        result = 31 * result + (rootDirToStoreBackUps != null ? rootDirToStoreBackUps.hashCode() : 0);
        result = 31 * result + numberOfBackups;
        result = 31 * result + (backUpSubDirs != null ? backUpSubDirs.hashCode() : 0);
        result = 31 * result + (backUpToReplace != null ? backUpToReplace.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "dirToBackUp=" + pathToFiles +
                ", rootDirToSave=" + rootDirToStoreBackUps +
                ", numberOfBackups=" + numberOfBackups +
                ", backUpDirName='" + backUpSubDirs + '\'' +
                ", backUpToReplace=" + backUpToReplace +
                '}';
    }
}
