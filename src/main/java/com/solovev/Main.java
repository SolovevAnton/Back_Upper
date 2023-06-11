package com.solovev;

import com.solovev.model.Configuration;
import com.solovev.repository.FilesRepo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        Path testingFolder = Path.of("TestFolder");
        Path storingFolderRoot = Path.of("SaveFolder");

        Configuration conf = new Configuration(testingFolder);
        conf.setRootDirToStoreBackUps(storingFolderRoot);

        FilesRepo repo = new FilesRepo(conf);
        System.out.println(repo);

    }
}