package com.span.psrp.apache.camel.topics.transformation.csv;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = ",", crlf = "UNIX")
public class BookModel {

    @DataField(pos = 1)
    private String category;

    @DataField(pos = 2)
    private String title;

    @DataField(pos = 3, defaultValue = "en")
    private String titleLanguage;

    @DataField(pos = 4)
    private String author1;

    @DataField(pos = 5)
    private String author2;

    @DataField(pos = 6, pattern = "MMM-yyyy")
    private Date publishDate;

    @DataField(pos = 7, precision = 2)
    private BigDecimal price;

    public String getAuthor1() {
        return author1;
    }

    public void setAuthor1(String author1) {
        this.author1 = author1;
    }

    public String getAuthor2() {
        return author2;
    }

    public void setAuthor2(String author2) {
        this.author2 = author2;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleLanguage() {
        return titleLanguage;
    }

    public void setTitleLanguage(String titleLanguage) {
        this.titleLanguage = titleLanguage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookModel bookModel = (BookModel) o;

        if (author1 != null ? !author1.equals(bookModel.author1) : bookModel.author1 != null) return false;
        if (author2 != null ? !author2.equals(bookModel.author2) : bookModel.author2 != null) return false;
        if (category != null ? !category.equals(bookModel.category) : bookModel.category != null) return false;
        if (price != null ? !price.equals(bookModel.price) : bookModel.price != null) return false;
        if (publishDate != null ? !publishDate.equals(bookModel.publishDate) : bookModel.publishDate != null)
            return false;
        if (title != null ? !title.equals(bookModel.title) : bookModel.title != null) return false;
        if (titleLanguage != null ? !titleLanguage.equals(bookModel.titleLanguage) : bookModel.titleLanguage != null)
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "BookModel{" +
            "category='" + category + '\'' +
            ", title='" + title + '\'' +
            ", titleLanguage='" + titleLanguage + '\'' +
            ", author1='" + author1 + '\'' +
            ", author2='" + author2 + '\'' +
            ", publishDate=" + publishDate +
            ", price=" + price +
            '}';
    }
}

