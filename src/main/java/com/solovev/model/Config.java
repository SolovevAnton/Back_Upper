package com.solovev.model;

import java.nio.file.Path;
import java.util.Objects;

/**
 * Class to store config for every folder that needs to be backUpped
 */
public class Config {
    private static final Path DIR_LAST_REPLACED_BACKUP = Path.of("last_replaced_backup");
    private final Path dirToBackUp;
    private Path rootDirToSave = Path.of(System.getProperty("user.dir"));
    private int numberOfBackups = 1; // default is 1
    private String backUpDirName = "Back_up";
    private Path backUpToReplace;

    public Config() {
        this.dirToBackUp = null; // default wii not work
    }

    public Config(Path dirToBackUp) {
        this.dirToBackUp = dirToBackUp;
    }

    public Config(Path dirToBackUp, Path rootDirToSave, int numberOfBackups, String backUpDirName, Path backUpToReplace) {
        this.dirToBackUp = dirToBackUp;
        this.rootDirToSave = rootDirToSave;
        this.numberOfBackups = numberOfBackups;
        this.backUpDirName = backUpDirName;
        this.backUpToReplace = backUpToReplace;
    }

    public Path getDirToBackUp() {
        return dirToBackUp;
    }

    public Path getRootDirToSave() {
        return rootDirToSave;
    }

    public void setRootDirToSave(Path rootDirToSave) {
        this.rootDirToSave = rootDirToSave;
    }

    public int getNumberOfBackups() {
        return numberOfBackups;
    }

    public void setNumberOfBackups(int numberOfBackups) {
        this.numberOfBackups = numberOfBackups;
    }

    public String getBackUpDirName() {
        return backUpDirName;
    }

    public void setBackUpDirName(String backUpDirName) {
        this.backUpDirName = backUpDirName;
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

        Config config = (Config) o;

        if (numberOfBackups != config.numberOfBackups) return false;
        if (!Objects.equals(dirToBackUp, config.dirToBackUp)) return false;
        if (!Objects.equals(rootDirToSave, config.rootDirToSave))
            return false;
        if (!Objects.equals(backUpDirName, config.backUpDirName))
            return false;
        return Objects.equals(backUpToReplace, config.backUpToReplace);
    }

    @Override
    public int hashCode() {
        int result = dirToBackUp != null ? dirToBackUp.hashCode() : 0;
        result = 31 * result + (rootDirToSave != null ? rootDirToSave.hashCode() : 0);
        result = 31 * result + numberOfBackups;
        result = 31 * result + (backUpDirName != null ? backUpDirName.hashCode() : 0);
        result = 31 * result + (backUpToReplace != null ? backUpToReplace.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Config{" +
                "dirToBackUp=" + dirToBackUp +
                ", rootDirToSave=" + rootDirToSave +
                ", numberOfBackups=" + numberOfBackups +
                ", backUpDirName='" + backUpDirName + '\'' +
                ", backUpToReplace=" + backUpToReplace +
                '}';
    }
}
