package ch.giraffi.base;

import io.quarkus.runtime.RuntimeValue;
import io.quarkus.runtime.annotations.Recorder;

@Recorder
public class AppInfoRecorder {

    public RuntimeValue<BuildInfo> createBuildInfo(String time, String builtFor) {
        return new RuntimeValue<>(new BuildInfo(time, builtFor));
    }
}
