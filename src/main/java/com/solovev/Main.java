package com.solovev;

import com.solovev.model.Configuration;
import com.solovev.model.SavingSubDirsManager;
import com.solovev.repository.ManagersRepoFromFile;
import com.solovev.util.BackUpMakers;
import com.solovev.util.Constants;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        Path testingFolder = Path.of("TestFolder", "new_data", "otherFolder");
        Path storingFolderRoot = Path.of("SavingData");

        Configuration conf = new Configuration(testingFolder);
        conf.setRootDirToStoreBackUps(storingFolderRoot);

        SavingSubDirsManager savingSubDirsManager = new SavingSubDirsManager("Test", conf);

        ManagersRepoFromFile repo = new ManagersRepoFromFile(Constants.SAVE_JSON.getPath());
       // repo.add(savingSubDirsManager);

        BackUpMakers.doBackUps(repo);

    }
}