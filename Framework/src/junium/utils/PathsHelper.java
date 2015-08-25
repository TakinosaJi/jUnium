package junium.utils;

import java.io.File;
import java.nio.file.Paths;

public class PathsHelper {
    public static String GetAbsoluteWorkingDirPath(){
        return Paths.get("").toAbsolutePath().toString() + File.separator;
    }
}
