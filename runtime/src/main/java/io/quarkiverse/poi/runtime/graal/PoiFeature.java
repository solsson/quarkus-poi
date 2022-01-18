package io.quarkiverse.poi.runtime.graal;

import org.graalvm.nativeimage.ImageSingletons;
import org.graalvm.nativeimage.hosted.Feature;
import org.graalvm.nativeimage.impl.RuntimeClassInitializationSupport;

import com.oracle.svm.core.annotate.AutomaticFeature;

/**
 * Note that this initialization is not enough if user wants to deserialize actual images
 * (e.g. from XML). POI Extension must be loaded for decoding JDK supported image formats.
 */
@AutomaticFeature
public class PoiFeature implements Feature {

    @Override
    public void afterRegistration(AfterRegistrationAccess access) {
        final RuntimeClassInitializationSupport runtimeInit = ImageSingletons.lookup(RuntimeClassInitializationSupport.class);
        final String reason = "Quarkus run time init for POI";
        runtimeInit.initializeAtRunTime("org.apache.poi.hssf.util", reason);
        runtimeInit.initializeAtRunTime("org.apache.poi.ss.format", reason);
        runtimeInit.initializeAtRunTime("org.apache.poi.ss.util", reason);
    }
}
