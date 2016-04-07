package exdevlab.junium.utils;

import java.io.File;
import java.util.Comparator;

public class FileByCreationTimeComparator implements Comparator<File> {
    public int compare(File f1, File f2) {
        return Long.compare(f1.lastModified(), f2.lastModified());
    }
}

