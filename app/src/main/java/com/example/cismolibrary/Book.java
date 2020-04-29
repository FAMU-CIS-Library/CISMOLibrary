package com.example.famucismolibrary;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

@ParseClassName("Book")
public class Book extends ParseObject {

    public static final String KEY_BOOKTITLE = "bookTitle";
    public static final String KEY_AUTHOR = "author";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_EDITION = "edition";
    public static final String KEY_PAGENUM = "pageNum";
    public static final String KEY_CONDITION = "condition";
    public static final String KEY_TOTALQUANTITY = "totalQuantity";
    public static final String KEY_FREEQUANTITY = "freeQuantity";
    public static final String KEY_BOOKCOVER = "bookCover";
    public static final String KEY_RENTEDBY = "rentedBy";

    // Getters
    public String getAuthor() { return getString(KEY_AUTHOR); }

    public String getDescription() { return getString(KEY_DESCRIPTION); }

    public String getBookTitle() { return getString(KEY_BOOKTITLE); }

    public String getEdition() { return getString(KEY_EDITION);  }

    public int getPageNum() { return getInt(KEY_PAGENUM); }

    public String getCondition() { return getString(KEY_CONDITION); }

    public int getTotalQuantity() { return getInt(KEY_TOTALQUANTITY); }

    public int getFreeQuantity() { return getInt(KEY_FREEQUANTITY);  }

    public ParseFile getBookCover() { return getParseFile(KEY_BOOKCOVER);  }

    // Get student who rents this book from parse
    //public ParseUser getRentedBy() { return (); }

    //Setters

    public void setAuthor(String author) { put(KEY_AUTHOR, author); }

    public void setDescription(String description) { put(KEY_DESCRIPTION, description); }

    public void setBookTitle(String title) { put(KEY_BOOKTITLE, title); }

    public void setEdition(String edition) { put(KEY_EDITION, edition);  }

    public void setPageNum(int pages) { put(KEY_PAGENUM, pages); }

    public void setCondition(String condition) { put(KEY_CONDITION, condition); }

    public void setTotalQuantity(int totalQuantity) { put(KEY_TOTALQUANTITY, totalQuantity); }

    public void setFreeQuantity(int freeQuantity) { put(KEY_FREEQUANTITY, freeQuantity);  }

    public void setBookCover(ParseFile parseCover) { put(KEY_FREEQUANTITY, parseCover);  }

    //public void setRentedBy(Student student) { put(KEY_RENTEDBY, student );  }
}
