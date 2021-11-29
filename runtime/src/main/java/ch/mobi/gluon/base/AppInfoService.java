package ch.mobi.gluon.base;

import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.spi.ConfigSource;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Singleton;
import java.time.Instant;
import java.util.Map;
import java.util.logging.Logger;

@Singleton
public class AppInfoService {

    private static final Logger logger = Logger.getLogger(AppInfoService.class.getName());

    private final Config config;
    private final String createTime;
    private String startupTime;

    public AppInfoService() {
        this.config = ConfigProvider.getConfig();
        this.createTime = Instant.now().toString();
    }

    void onStart(@Observes StartupEvent ev) {
        String now = Instant.now().toString();
        logger.info("AppInfoService Startup: " + now);
        startupTime = now;
    }

    private BuildInfo getBuildTimeInfo() {
        return CDI.current().select(BuildInfo.class).get();
    }

    public AppInfo getAppInfo() {
        AppInfo ai = new AppInfo();

        ai.setBuildTime(this.getBuildTimeInfo().getTime());
        ai.setBuiltFor(this.getBuildTimeInfo().getBuiltFor());

        ai.setRunBy(getConfig("run-by", String.class));
        ai.setStartupTime(this.startupTime);
        ai.setCreateTime(this.createTime);
        ai.setCurrentTime(Instant.now().toString());
        ai.setApplicationName(config.getValue("quarkus.application.name", String.class));
        ai.setApplicationVersion(config.getValue("quarkus.application.version", String.class));
        ai.setPropertiesString(collectProperties());

        return ai;
    }

    private <T> T getConfig(String propertyName, Class<T> propertyType) {
        return config.getValue(AppInfoNames.CONFIG_PREFIX+"."+propertyName, propertyType);
    }

    private String collectProperties() {
        StringBuilder sb = new StringBuilder();
        for (ConfigSource configSource : config.getConfigSources()) {
            sb.append(String.format("%n%s %s%n", "ConfigSource:", configSource.getName()));
            for (Map.Entry<String, String> property : configSource.getProperties().entrySet()) {
                sb.append(String.format("   %-40s %s%n", property.getKey(), property.getValue()));
            }
        }

        return sb.toString();
    }
}
