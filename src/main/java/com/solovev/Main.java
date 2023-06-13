package com.solovev;

import com.solovev.model.Configuration;
import com.solovev.model.BackUpMaker;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        Path testingFolder = Path.of("C:","Users","Anton","iCloudDrive","iCloud~md~obsidian");
        Path storingFolderRoot = Path.of("D:\\Temp","SavingData");

        Configuration conf = new Configuration(testingFolder);
        conf.setRootDirToStoreBackUps(storingFolderRoot);

        BackUpMaker maker = new BackUpMaker(conf);
        maker.doBackUp();

    }
}