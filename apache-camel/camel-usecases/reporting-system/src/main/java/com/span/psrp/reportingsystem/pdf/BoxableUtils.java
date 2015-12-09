package com.span.psrp.reportingsystem.pdf;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;

public class BoxableUtils {

    public static final PDTrueTypeFont loadFont(final PDDocument document, final String fontPath) throws IOException {
        return PDTrueTypeFont.loadTTF(document, BoxableUtils.class.getClassLoader().getResourceAsStream(fontPath));
    }
}
