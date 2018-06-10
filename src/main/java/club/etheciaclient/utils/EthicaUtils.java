package club.etheciaclient.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EthicaUtils {

    public static final Logger LOGGER = LogManager.getLogger("Ethecia Client");

    public int getBuild() {
        return 1;
    }

    public String getVersion() {
        return "Beta 1.0";
    }
}
