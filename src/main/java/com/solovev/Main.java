package com.solovev;

import com.solovev.model.Configuration;
import com.solovev.model.BackUpMaker;
import com.solovev.model.SavingSubDirsManager;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        Path testingFolder = Path.of("TestFolder");
        Path storingFolderRoot = Path.of( "SavingData");

        Configuration conf = new Configuration(testingFolder);
        conf.setRootDirToStoreBackUps(storingFolderRoot);
        SavingSubDirsManager savingSubDirsManager = new SavingSubDirsManager("Test",conf);
        BackUpMaker maker = new BackUpMaker(savingSubDirsManager);
        maker.doBackUp();

    }
}