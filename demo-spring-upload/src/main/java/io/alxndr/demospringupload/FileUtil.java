package io.alxndr.demospringupload;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;

public class FileUtil {

    final static String path = System.getProperty("user.home") + File.separator;

    public static String getPath(FileType fileType) {
        return null;
    }

    @Getter
    enum FileType {
        IMAGE("images", "이미지"),
        PROFILE("profile", "프로필"),
        DOCUMENT("document", "문서");

        private String path;
        private String description;

        FileType(String path, String description) {
            this.path = path;
            this.description = description;
        }
    }

}
