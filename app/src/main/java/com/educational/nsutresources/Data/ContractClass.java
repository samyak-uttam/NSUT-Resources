package com.educational.nsutresources.Data;

import android.provider.BaseColumns;

public class ContractClass {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private ContractClass() {
    }

    public static final class BookEntry implements BaseColumns {

        public final static String TABLE_NAME = "book";

        /**
         * Unique ID number for the book (only for use in the database table).
         * <p>
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the book.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_BOOK_NAME = "name";

        /**
         * Data type of the book.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_BOOK_DATA = "data";
    }
}
