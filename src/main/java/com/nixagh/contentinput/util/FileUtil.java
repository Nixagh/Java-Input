package com.nixagh.contentinput.util;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author nghia.nguyen-dinh
 * @since 11/2/2023 at 1:08 PM
 */
@Component
public class FileUtil {
    public File getFile(String path) {
        return new File(path);
    }
}
