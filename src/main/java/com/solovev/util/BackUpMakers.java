package com.solovev.util;

import com.solovev.model.BackUpMaker;
import com.solovev.model.SavingSubDirsManager;
import com.solovev.repository.ManagersRepoInterface;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * Class with only static methods to do backUps and update state for all managers repo
  */
public class BackUpMakers {
    public static void doBackUps(ManagersRepoInterface repo) throws IOException {
        Consumer<SavingSubDirsManager> doingBackUp = manager -> {
            try {
                new BackUpMaker(manager).doBackUp();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
        //does backUp and update state of the makers
        repo
                .getData()
                .stream()
                .forEach(doingBackUp);

        //saves the state of the managers in the repo
        repo.save();
    }
}
