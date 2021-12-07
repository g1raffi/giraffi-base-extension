package ch.giraffi.base;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

@ConfigRoot(name = AppInfoNames.EXTENSION_NAME, phase = ConfigPhase.RUN_TIME)
public class AppInfoRuntimeConfig {

    /**
     * String to determin who's running the application
     *
     */
    @ConfigItem
    String runBy;
}
