package com.span.psrp.reportingsystem.pdf;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;

/**
 * Created by dgautier on 3/18/2015.
 */
public abstract class AbstractTemplatedTable<T extends AbstractPageTemplate> extends Table<T> {

    public AbstractTemplatedTable(final float yStart, final float yStartNewPage, final float bottomMargin, final float width, final float margin, final PDDocument document,
            final T currentPage, final boolean drawLines,
            final boolean drawContent) throws IOException {
        super(yStart, yStartNewPage, bottomMargin, width, margin, document, currentPage, drawLines, drawContent);
    }

    public AbstractTemplatedTable(final float yStartNewPage, final float bottomMargin, final float width, final float margin, final PDDocument document, final boolean drawLines,
            final boolean drawContent)
                    throws IOException {
        super(yStartNewPage, bottomMargin, width, margin, document, drawLines, drawContent);
        setYStart(getCurrentPage().yStart());
    }

}
