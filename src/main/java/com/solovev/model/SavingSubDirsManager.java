package com.solovev.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * class stores in file the subdir names for back up, and contains config
 * This class is done to be serialized to store the state of the backUps
 */
public class SavingSubDirsManager {
    private String name;
    private final Path dirNameForLastReplacedBackUp = Path.of("last_replaced_backup");

    //Queue of already done backUps. gets updated, when new dir name is requested
    private Queue<Path> subDirs = new ArrayDeque<>();
    private final Configuration config;
    private final DateTimeFormatter localDateTimePattern = DateTimeFormatter.ofPattern("_yyyy_MM_dd_HHmm");

    public SavingSubDirsManager() {
        this.name = "Default";
        config = new Configuration();
    }

    public SavingSubDirsManager(String name, Configuration config) {
        this.name = name;
        this.config = config;
    }

    public SavingSubDirsManager(String name, Queue<Path> subDirs, Configuration config) {
        this.name = name;
        this.subDirs = subDirs;
        this.config = config;
    }

    /**
     * Returns back up dir absolute path
     *
     * @return dir name to save file
     */
    @JsonIgnore
    public Path getBackUpDir() {
        //creates a name of subdir based on the initial subdir name and date pattern
        Path fullSubdirName = Path.of(
                config.getBackUpSubDirs() +
                        LocalDateTime.now().format(localDateTimePattern));

        Path pathToSave = config.getRootDirToStoreBackUps()
                .resolve(fullSubdirName)
                .toAbsolutePath();

        if (peekPathToReplace().isPresent()) {
            subDirs.poll();
        }

        subDirs.add(pathToSave);
        return pathToSave;
    }

    /**
     * Method shows path to be replaced next, or empty optional if que is not full after actualization
     *
     * @return Path to replace or empty optional
     */
    public Optional<Path> peekPathToReplace() {
        actualize();
        return subDirs.size() == config.getNumberOfBackups()
                ? Optional.of(subDirs.peek())
                : Optional.empty();
    }

    public Configuration getConfig() {
        return config;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Queue<Path> getSubDirs() {
        return subDirs;
    }

    public void setSubDirs(Queue<Path> subDirs) {
        this.subDirs = subDirs;
    }

    /**
     * Checks if all path present in subDirs exists, REMOVES all path, that does not exist anymore
     */
    private void actualize() {
        subDirs = subDirs
                .stream()
                .filter(Files::exists)
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    /**
     * Note formatter is removed from equals, since doesn't have proper equals code, as well as subDirs;
     * SubDirs are removed also because the same state managers cannot have different history
     *
     * @param o object to compare
     * @return true if objects are logically the same
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SavingSubDirsManager that = (SavingSubDirsManager) o;

        if (!Objects.equals(name, that.name)) return false;
        if (!dirNameForLastReplacedBackUp.equals(that.dirNameForLastReplacedBackUp)) return false;
        return Objects.equals(config, that.config);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + dirNameForLastReplacedBackUp.hashCode();
        result = 31 * result + (config != null ? config.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SavingSubDirsManager{" +
                "name='" + name + '\'' +
                ", dirNameForLastReplacedBackUp=" + dirNameForLastReplacedBackUp +
                ", subDirs=" + subDirs +
                ", config=" + config +
                ", localDateTimePattern=" + localDateTimePattern +
                '}';
    }
}
