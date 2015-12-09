package com.span.psrp.reportingsystem.pdf;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class BaseTable extends Table<PDPage> {

    public BaseTable(final float yStart, final float yStartNewPage, final float bottomMargin, final float width, final float margin, final PDDocument document,
            final PDPage currentPage, final boolean drawLines,
            final boolean drawContent) throws IOException {
        super(yStart, yStartNewPage, bottomMargin, width, margin, document, currentPage, drawLines, drawContent);
    }

    @Override
    protected void loadFonts() {
        // Do nothing as we don't have any fonts to load
    }

    @Override
    protected PDPage createPage() {
        PDPage newPage = new PDPage();
        getDocument().addPage(newPage);
        return newPage;
    }
}
