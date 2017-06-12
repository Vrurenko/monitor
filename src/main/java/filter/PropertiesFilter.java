package filter;

import java.io.File;
import java.io.FileFilter;

public class PropertiesFilter implements FileFilter {

    public boolean accept(File pathname) {
        return pathname.isFile() && pathname.getName().endsWith(".properties");
    }

}
