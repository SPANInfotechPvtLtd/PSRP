package com.span.psrp.reportingsystem.pdf;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.span.psrp.reportingsystem.model.AccountInfo;
import com.span.psrp.reportingsystem.model.CustomerInfo;

public final class GeneratePdfTable {

    private GeneratePdfTable() {
        // Never instantiate
    }

    public static void preparePdf(final ReportDetails reportDetails) {
        // Initialize Document
        PDDocument doc = new PDDocument();
        PDPage page = addNewPage(doc);
        prepareCustomerTable(reportDetails.getCustomerInfo(), doc, page);
        prepareAccountTable(reportDetails.getAccountInfo(), doc, page);
        // Close Stream and save pdf
        File file = new File("target/report/report-sample.pdf");
        try {
            doc.save(file);
            doc.close();
        } catch (COSVisitorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BaseTable prepareCustomerTable(final CustomerInfo customerInfo, final PDDocument doc, final PDPage page) {
        if (null != customerInfo) {
            BaseTable custTable = null;
            // Set margins
            float margin = 10;
            float yStartNewPage = page.findMediaBox().getHeight()
                    - (2 * margin);
            // Initialize table
            float tableWidth = page.findMediaBox().getWidth() - (2 * margin);
            boolean drawContent = true;
            float yStart = yStartNewPage;
            float bottomMargin = 70;
            try {
                custTable = new BaseTable(yStart, yStartNewPage, bottomMargin, tableWidth, margin, doc, page, true, drawContent);
                // Create Header row
                Row headerRow = custTable.createRow(15f);
                Cell cell = headerRow.createCell(100, "Customer Details");
                cell.setFont(PDType1Font.HELVETICA_BOLD);
                cell.setFillColor(Color.BLACK);
                cell.setTextColor(Color.WHITE);
                custTable.setHeader(headerRow);
                // Create 2 column row
                Row row = custTable.createRow(15f);

                cell = row.createCell(9, "Customer Id");
                cell.setFont(PDType1Font.HELVETICA);

                cell = row.createCell(9, "Customer No");
                cell.setFont(PDType1Font.HELVETICA);

                cell = row.createCell(9, "First Name");
                cell.setFont(PDType1Font.HELVETICA_OBLIQUE);

                cell = row.createCell(9, "Last Name");
                cell.setFont(PDType1Font.HELVETICA_OBLIQUE);

                cell = row.createCell(9, "Mobile");
                cell.setFont(PDType1Font.HELVETICA_OBLIQUE);

                cell = row.createCell(9, "Address 1");
                cell.setFont(PDType1Font.HELVETICA_OBLIQUE);

                cell = row.createCell(9, "Address 2");
                cell.setFont(PDType1Font.HELVETICA_OBLIQUE);

                cell = row.createCell(9, "City");
                cell.setFont(PDType1Font.HELVETICA_OBLIQUE);

                cell = row.createCell(9, "State");
                cell.setFont(PDType1Font.HELVETICA_OBLIQUE);

                cell = row.createCell(9, "Pincode");
                cell.setFont(PDType1Font.HELVETICA_OBLIQUE);

                cell = row.createCell(9, "Country");
                cell.setFont(PDType1Font.HELVETICA_OBLIQUE);

                row = custTable.createRow(10f);
                cell = row.createCell(9, (null != customerInfo.getCustomerId()) ? String.valueOf(customerInfo.getCustomerId()) : "");
                cell.setFont(PDType1Font.HELVETICA_OBLIQUE);
                cell.setFontSize(6);

                cell = row.createCell(9, customerInfo.getCustomerNo());
                cell.setFont(PDType1Font.HELVETICA_OBLIQUE);
                cell.setFontSize(6);

                cell = row.createCell(9, customerInfo.getFirstName());
                cell.setFont(PDType1Font.HELVETICA_OBLIQUE);
                cell.setFontSize(6);

                cell = row.createCell(9, customerInfo.getLastName());
                cell.setFont(PDType1Font.HELVETICA_OBLIQUE);
                cell.setFontSize(6);

                cell = row.createCell(9, customerInfo.getPhone());
                cell.setFont(PDType1Font.HELVETICA_OBLIQUE);
                cell.setFontSize(6);

                cell = row.createCell(9, customerInfo.getAddressLine1());
                cell.setFont(PDType1Font.HELVETICA_OBLIQUE);
                cell.setFontSize(6);

                cell = row.createCell(9, customerInfo.getAddressLine2());
                cell.setFont(PDType1Font.HELVETICA_OBLIQUE);
                cell.setFontSize(6);

                cell = row.createCell(9, customerInfo.getCity());
                cell.setFont(PDType1Font.HELVETICA_OBLIQUE);
                cell.setFontSize(6);

                cell = row.createCell(9, customerInfo.getState());
                cell.setFont(PDType1Font.HELVETICA_OBLIQUE);
                cell.setFontSize(6);

                cell = row.createCell(9, customerInfo.getPostalCode());
                cell.setFont(PDType1Font.HELVETICA_OBLIQUE);
                cell.setFontSize(6);

                cell = row.createCell(9, customerInfo.getCountry());
                cell.setFont(PDType1Font.HELVETICA_OBLIQUE);
                cell.setFontSize(6);

                custTable.draw();
            } catch (IOException ie) {
                ie.printStackTrace();
            }
            return custTable;
        }
        return null;
    }

    public static BaseTable prepareAccountTable(final List<AccountInfo> accountInfos, final PDDocument doc, final PDPage page) {
        if (null != accountInfos) {
            BaseTable acctTable = null;
            // Set margins
            float margin = 40;
            float yStartNewPage = page.findMediaBox().getHeight() - (2 * margin);
            // Initialize table
            float tableWidth = page.findMediaBox().getWidth() - (2 * margin);
            boolean drawContent = true;
            float yStart = yStartNewPage;
            float bottomMargin = 70;
            try {
                acctTable = new BaseTable(yStart, yStartNewPage, bottomMargin, tableWidth, margin, doc, page, true, drawContent);
                // Create Header row
                Row headerRow = acctTable.createRow(15f);
                Cell cell = headerRow.createCell(100, "Account Details");
                cell.setFont(PDType1Font.HELVETICA_BOLD);
                cell.setFillColor(Color.BLACK);
                cell.setTextColor(Color.WHITE);
                acctTable.setHeader(headerRow);

                // Create 2 column row
                Row row = acctTable.createRow(15f);

                cell = row.createCell(25, "Account Id");
                cell.setFont(PDType1Font.HELVETICA);

                cell = row.createCell(25, "Account No");
                cell.setFont(PDType1Font.HELVETICA);

                cell = row.createCell(25, "Account type");
                cell.setFont(PDType1Font.HELVETICA_OBLIQUE);

                cell = row.createCell(25, "Customer Id");
                cell.setFont(PDType1Font.HELVETICA_OBLIQUE);

                for (AccountInfo acctInfo : accountInfos) {
                    // Account data
                    row = acctTable.createRow(10f);
                    cell = row.createCell(25, String.valueOf(acctInfo.getAccountId()));
                    cell.setFont(PDType1Font.HELVETICA);
                    cell.setFontSize(6);

                    cell = row.createCell(25, acctInfo.getAccountNo());
                    cell.setFont(PDType1Font.HELVETICA_OBLIQUE);
                    cell.setFontSize(6);

                    cell = row.createCell(25, acctInfo.getAccountType());
                    cell.setFont(PDType1Font.HELVETICA_OBLIQUE);
                    cell.setFontSize(6);

                    cell = row.createCell(25, acctInfo.getCustomerId());
                    cell.setFont(PDType1Font.HELVETICA_OBLIQUE);
                    cell.setFontSize(6);
                }
                acctTable.draw();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return acctTable;
        }
        return null;

    }

    private static PDPage addNewPage(final PDDocument doc) {
        PDPage page = new PDPage();
        doc.addPage(page);
        return page;
    }
}
