package com.solovev.model;

import java.nio.file.Path;
import java.util.Objects;

/**
 * Class to store config for every folder that needs to be backUpped
 */
public class Configuration {
    private final Path targetDir; //cannot be same as rootSave and check for existence
    private Path rootDirToStoreBackUps = Path.of(System.getProperty("user.dir")).toAbsolutePath();
    private int numberOfBackups = 1; // default is 1 and cannot be less than 1
    private String backUpSubDirs = "Back_up"; //must be a dir

    public Configuration() {
        this.targetDir = Path.of("").toAbsolutePath(); // default will not work
    }

    public Configuration(Path pathToBackUp) {
        this.targetDir = pathToBackUp.toAbsolutePath();
    }
    //toDo add data check 1. backUp cannot be same as target
    public Configuration(Path pathToBackUp, Path rootDirToSave, int numberOfBackups, String backUpDirName) {
        this.targetDir = pathToBackUp.toAbsolutePath();
        this.rootDirToStoreBackUps = rootDirToSave.toAbsolutePath();
        this.numberOfBackups = numberOfBackups;
        this.backUpSubDirs = backUpDirName;
    }
    public Path getTargetDir() {
        return targetDir;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Configuration that = (Configuration) o;
        return numberOfBackups == that.numberOfBackups && Objects.equals(targetDir, that.targetDir) && Objects.equals(rootDirToStoreBackUps, that.rootDirToStoreBackUps) && Objects.equals(backUpSubDirs, that.backUpSubDirs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetDir, rootDirToStoreBackUps, numberOfBackups, backUpSubDirs);
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "pathToFiles=" + targetDir +
                ", rootDirToStoreBackUps=" + rootDirToStoreBackUps +
                ", numberOfBackups=" + numberOfBackups +
                ", backUpSubDirs='" + backUpSubDirs + '\'' +
                '}';
    }
}
