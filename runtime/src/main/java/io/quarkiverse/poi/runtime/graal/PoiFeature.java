package io.quarkiverse.poi.runtime.graal;

import com.oracle.svm.core.annotate.AutomaticFeature;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.format.CellFormat;
import org.apache.poi.ss.format.CellFormatPart;
import org.apache.poi.ss.format.CellTextFormatter;
import org.apache.poi.ss.util.SheetUtil;
import org.graalvm.nativeimage.ImageSingletons;
import org.graalvm.nativeimage.hosted.Feature;
import org.graalvm.nativeimage.impl.RuntimeClassInitializationSupport;

/**
 * Note that this initialization is not enough if user wants to deserialize actual images
 * (e.g. from XML). POI Extension must be loaded for decoding JDK supported image formats.
 */
@AutomaticFeature
public class PoiFeature implements Feature {

    /**
     * 因为依赖 quarkus-awt 部分类需要提前初始化
     * 通过 --trace-class-initialization= 参数捕捉需要初始化的类
     * <p>
     * 可以在主项目 application.properties 中增加以下配置
     * quarkus.native.additional-build-args=--trace-class-initialization=sun.awt.AWTAccessor\\,java.awt.RenderingHints\\,java.awt.Color\\,sun.awt.SunHints\\,java.awt.Toolkit
     *
     * @param access
     */
    @Override
    public void afterRegistration(AfterRegistrationAccess access) {
        final RuntimeClassInitializationSupport runtimeInit = ImageSingletons.lookup(RuntimeClassInitializationSupport.class);
        final String reason = "Quarkus run time init for POI";
        runtimeInit.initializeAtRunTime(HSSFColor.HSSFColorPredefined.class, reason);
        runtimeInit.initializeAtRunTime(CellFormatPart.class, reason);
        runtimeInit.initializeAtRunTime(CellTextFormatter.class, reason);
        runtimeInit.initializeAtRunTime(CellFormat.class, reason);
        runtimeInit.initializeAtRunTime(SheetUtil.class, reason);
    }
}
