package com.solovev.util;

import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Some constant passes (for example for saving configs)
 */
public enum Constants {
    SAVE_JSON("src/main/resources/save.json");

    private final Path path;

    Constants(String relativePath) {
        this.path = Path.of(relativePath).toAbsolutePath();
        path.toFile().getParentFile().mkdirs(); // to make sure the file will be present, when used
        try{
            if(!Files.exists(path)){
                Files.createFile(path);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Path getPath() {
        return path;
    }
}
