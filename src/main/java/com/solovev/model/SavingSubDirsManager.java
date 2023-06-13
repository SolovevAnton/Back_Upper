package com.solovev.model;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * class stores in file the subdir names for back up
 */
public class SavingSubDirsManager {
    private Queue<Path> subDirs = new ArrayDeque<>();
    private final Configuration config;
    private final DateTimeFormatter localDateTimePattern = DateTimeFormatter.ofPattern("_yyyy_MM_dd_HHmm");

    public SavingSubDirsManager(Configuration config) {
        this.config = config;
    }

    /**
     * Returns back up dir absolute path
     *
     * @return dir name to save file
     */
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
     * @return Path to replace or empty optional
     */
    public Optional<Path> peekPathToReplace(){
        actualize();
        return subDirs.size() == config.getNumberOfBackups()
                ? Optional.of(subDirs.peek())
                : Optional.empty();
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
}