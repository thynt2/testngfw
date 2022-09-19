package common;

import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
public class Constants {
    private static Constants globalInstance;

    private Constants() {

    }

    public static synchronized Constants getConstants() {
        if (globalInstance == null) {
            globalInstance = new Constants();
        }
        return globalInstance;
    }

    public final long longTimeOut = 30;
    public final long shortTimeout = 5;
    public final String projectPath = System.getProperty("user.dir");
    public final String osName = System.getProperty("os.name");
    public final String uploadFilePath = projectPath + File.separator + "uploadFiles" + File.separator;
    public final String downloadFilePath = projectPath + File.separator + "downloadFiles" + File.separator;
}
