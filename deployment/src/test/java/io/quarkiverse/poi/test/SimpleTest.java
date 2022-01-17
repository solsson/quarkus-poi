package io.quarkiverse.poi.test;

import org.junit.jupiter.api.Test;
import org.openxmlformats.schemas.officeDocument.x2006.customProperties.impl.PropertiesDocumentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellType;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STFontScheme;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPatternType;

public class SimpleTest {
    @Test
    public void test() {
        System.out.println(PropertiesDocumentImpl.class.getCanonicalName());
        // .
        System.out.println(STCellType.Enum.class.getCanonicalName());
        // $
        System.out.println(STCellType.Enum.class.getName());
        System.out.println(STFontScheme.Enum.class.getName());
        System.out.println(STPatternType.Enum.class.getName());

    }
}
