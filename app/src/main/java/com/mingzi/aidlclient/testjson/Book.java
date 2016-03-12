package com.mingzi.aidlclient.testjson;

/**
 * Created by Administrator on 2016/3/11.
 */
public class Book {

    private String bookName;
    private Author mAuthor;

    public Book() {
    }

    public Book(String bookName, Author mAuthor) {
        this.bookName = bookName;
        this.mAuthor = mAuthor;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthor(Author mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getBookName() {
        return bookName;
    }

    public Author getAuthor() {
        return mAuthor;
    }
}
