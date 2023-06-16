package com.solovev;

import com.solovev.model.Configuration;
import com.solovev.model.BackUpMaker;
import com.solovev.model.SavingSubDirsManager;
import com.solovev.repository.ManagersRepo;
import com.solovev.util.Constants;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Path testingFolder = Path.of("TestFolder", "new_data", "otherFolder");
        Path storingFolderRoot = Path.of("SavingData");

        Configuration conf = new Configuration(testingFolder);
        conf.setRootDirToStoreBackUps(storingFolderRoot);

        SavingSubDirsManager savingSubDirsManager = new SavingSubDirsManager("Test", conf);

        ManagersRepo repo = new ManagersRepo(Constants.SAVE_JSON.getPath());
        repo.add(savingSubDirsManager);

        repo
                .getManagers()
                .stream()
                .forEach(manager -> {
                    try {
                        new BackUpMaker(manager).doBackUp();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        repo.save(Constants.SAVE_JSON.getPath());

    }
}