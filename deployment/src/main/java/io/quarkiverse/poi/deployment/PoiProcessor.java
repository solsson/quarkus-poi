package io.quarkiverse.poi.deployment;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.format.CellFormat;
import org.apache.poi.ss.format.CellFormatPart;
import org.apache.poi.ss.util.SheetUtil;
import org.jboss.jandex.DotName;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.*;

import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.CombinedIndexBuildItem;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.nativeimage.JniRuntimeAccessBuildItem;
import io.quarkus.deployment.builditem.nativeimage.ReflectiveClassBuildItem;

class PoiProcessor {
    private static final DotName POI_MODEL_PACKAGE = DotName
            .createSimple("org.openxmlformats.schemas.spreadsheetml.x2006.main.impl");

    private static final String FEATURE_POI = "poi";

    /**
     * {@link BuildStep} 意味着它被标识为 Quarkus 在部署期间必须执行的部署任务
     *
     * @return FeatureBuildItem 表示由扩展提供的功能。该功能的名称在应用程序引导期间显示在日志中。一个扩展最多应该提供一个功能。
     */
    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE_POI);
    }

    /**
     * {@link org.openxmlformats.schemas.officeDocument.x2006.customProperties.impl.CTPropertiesImpl}
     * {@link org.openxmlformats.schemas.officeDocument.x2006.customProperties.impl.PropertiesDocumentImpl}
     * {@link org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.impl.CTPropertiesImpl}
     * {@link org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.impl.PropertiesDocumentImpl}
     * {@link org.openxmlformats.schemas.officeDocument.x2006.relationships.impl.STRelationshipIdImpl}
     * {@link org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.impl.STXstringImpl}
     * {@link org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellType.Enum}
     * {@link org.openxmlformats.schemas.spreadsheetml.x2006.main.STFontScheme.Enum}
     * {@link org.openxmlformats.schemas.spreadsheetml.x2006.main.STPatternType.Enum}
     * {@link }
     *
     * @param reflectiveClass
     */
    @BuildStep
    void registerForReflection(CombinedIndexBuildItem combinedIndex,
            BuildProducer<ReflectiveClassBuildItem> reflectiveClass) {
        // since we only need reflection to the constructor of the class, we can specify `false` for both the methods and the fields arguments.
        reflectiveClass.produce(new ReflectiveClassBuildItem(true, false,
                "org.openxmlformats.schemas.officeDocument.x2006.customProperties.impl.CTPropertiesImpl",
                "org.openxmlformats.schemas.officeDocument.x2006.customProperties.impl.PropertiesDocumentImpl",
                "org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.impl.CTPropertiesImpl",
                "org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.impl.PropertiesDocumentImpl",
                "org.openxmlformats.schemas.officeDocument.x2006.relationships.impl.STRelationshipIdImpl",
                "org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.impl.STXstringImpl"));
        reflectiveClass.produce(new ReflectiveClassBuildItem(true, true,
                "org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellType$Enum",
                "org.openxmlformats.schemas.spreadsheetml.x2006.main.STFontScheme$Enum",
                "org.openxmlformats.schemas.spreadsheetml.x2006.main.STPatternType$Enum"));

        reflectiveClass.produce(new ReflectiveClassBuildItem(true, false,
                CTBookViewImpl.class.getCanonicalName(),
                CTBookViewsImpl.class.getCanonicalName(),
                CTBooleanPropertyImpl.class.getCanonicalName(),
                CTBorderImpl.class.getCanonicalName(),
                CTBorderPrImpl.class.getCanonicalName(),
                CTBordersImpl.class.getCanonicalName(),
                CTCellImpl.class.getCanonicalName(),
                CTCellStyleXfsImpl.class.getCanonicalName(),
                CTCellXfsImpl.class.getCanonicalName(),
                CTColImpl.class.getCanonicalName(),
                CTColorImpl.class.getCanonicalName(),
                CTColsImpl.class.getCanonicalName(),
                CTFillImpl.class.getCanonicalName(),
                CTFillsImpl.class.getCanonicalName(),
                CTFontFamilyImpl.class.getCanonicalName(),
                CTFontImpl.class.getCanonicalName(),
                CTFontNameImpl.class.getCanonicalName(),
                CTFontSchemeImpl.class.getCanonicalName(),
                CTFontSizeImpl.class.getCanonicalName(),
                CTFontsImpl.class.getCanonicalName(),
                CTNumFmtImpl.class.getCanonicalName(),
                CTNumFmtsImpl.class.getCanonicalName(),
                CTPageMarginsImpl.class.getCanonicalName(),
                CTPatternFillImpl.class.getCanonicalName(),
                CTRowImpl.class.getCanonicalName(),
                CTRstImpl.class.getCanonicalName(),
                CTSheetDataImpl.class.getCanonicalName(),
                CTSheetDimensionImpl.class.getCanonicalName(),
                CTSheetFormatPrImpl.class.getCanonicalName(),
                CTSheetImpl.class.getCanonicalName(),
                CTSheetsImpl.class.getCanonicalName(),
                CTSheetViewImpl.class.getCanonicalName(),
                CTSheetViewsImpl.class.getCanonicalName(),
                CTSstImpl.class.getCanonicalName(),
                CTStylesheetImpl.class.getCanonicalName(),
                CTWorkbookImpl.class.getCanonicalName(),
                CTWorkbookPrImpl.class.getCanonicalName(),
                CTWorksheetImpl.class.getCanonicalName(),
                CTXfImpl.class.getCanonicalName(),
                STBorderIdImpl.class.getCanonicalName(),
                STBorderStyleImpl.class.getCanonicalName(),
                STCellRefImpl.class.getCanonicalName(),
                STCellStyleXfIdImpl.class.getCanonicalName(),
                STCellTypeImpl.class.getCanonicalName(),
                STFillIdImpl.class.getCanonicalName(),
                STFontFamilyImpl.class.getCanonicalName(),
                STFontIdImpl.class.getCanonicalName(),
                STFontSchemeImpl.class.getCanonicalName(),
                STNumFmtIdImpl.class.getCanonicalName(),
                STPatternTypeImpl.class.getCanonicalName(),
                STRefImpl.class.getCanonicalName(),
                SstDocumentImpl.class.getCanonicalName(),
                StyleSheetDocumentImpl.class.getCanonicalName(),
                WorkbookDocumentImpl.class.getCanonicalName(),
                WorksheetDocumentImpl.class.getCanonicalName()));
    }

    /**
     * {@link org.apache.poi.hssf.util.HSSFColor}
     * {@link org.apache.poi.ss.format.CellFormatPart}
     * {@link org.apache.poi.ss.format.CellFormat}
     * {@link org.apache.poi.ss.util.SheetUtil}
     * {@link }
     */
    @BuildStep
    JniRuntimeAccessBuildItem setupRuntimeClasses() {
        return new JniRuntimeAccessBuildItem(true, true, true,
                HSSFColor.class.getCanonicalName(),
                CellFormatPart.class.getCanonicalName(),
                CellFormat.class.getCanonicalName(),
                SheetUtil.class.getCanonicalName());
    }
}
