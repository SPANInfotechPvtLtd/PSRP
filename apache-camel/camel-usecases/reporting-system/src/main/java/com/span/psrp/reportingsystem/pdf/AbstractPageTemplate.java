package com.span.psrp.reportingsystem.pdf;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;

public abstract class AbstractPageTemplate extends PDPage {

    protected abstract PDDocument getDocument();

    protected abstract float yStart();

    protected void addPicture(final PDJpeg ximage, final float cursorX, final float cursorY, final int width, final int height) throws IOException {

        PDPageContentStream contentStream = new PDPageContentStream(getDocument(), this, true, false);
        contentStream.drawXObject(ximage, cursorX, cursorY, width, height);
        contentStream.close();
    }

    protected PDJpeg loadPicture(final String nameJPGFile) throws IOException {
        InputStream inputStream = loadStream(nameJPGFile);
        BufferedImage awtImage = ImageIO.read(inputStream);
        return new PDJpeg(getDocument(), awtImage, 1.0f);
    }

    private InputStream loadStream(final String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }

}
