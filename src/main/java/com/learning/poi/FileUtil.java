package com.learning.poi;

import java.io.File;
import java.io.FilenameFilter;

public class FileUtil {

    public static File[] listFile(String path,String reg) {
        File dec = new File(path);
        if(dec.isDirectory()) {
            File[] files = dec.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    if(name.endsWith(reg)) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            return files;
        }
        return null;
    }
}
