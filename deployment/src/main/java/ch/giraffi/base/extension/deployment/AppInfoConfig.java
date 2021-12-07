package ch.giraffi.base.extension.deployment;

import ch.giraffi.base.AppInfoNames;
import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

@ConfigRoot(name = AppInfoNames.EXTENSION_NAME, phase = ConfigPhase.BUILD_TIME)
public class AppInfoConfig {

    /**
     * Config property describing who built the application
     *
     */
    @ConfigItem
    String builtFor;

    /**
     * Dummy
     *
     */
    @ConfigItem(defaultValue = "true")
    boolean recordBuildTime;

    /**
     * Dummy
     *
     */
    @ConfigItem(defaultValue = "false")
    boolean alwaysInclude;

    /**
     * Dummy
     *
     */
    @ConfigItem(defaultValue = AppInfoNames.EXTENSION_NAME)
    String basePath;
}
