package com.example.amanda.personaltrainerbusiness;





import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database {
    public Database(Database.DBOpenHelper DBOpenHelper) {
        this.DBOpenHelper = DBOpenHelper;
    }

    /**
     * Listing 8-1: Skeleton code for contract class constants
     */
    //The index (key) column name for use in where clauses.
    public static final String KEY_ID = "_id";

    //The name and column index of each column in your database.
    //These should be descriptive.
    public static final String KEY_ID_COLUMN =
            "KEY_ID_COLUMN";
    public static final String ADDRESS_COLUMN =
            "ADDRESS_COLUMN";
    public static final String PHONE_COLUMN =
            "PHONE_COLUMN";
    public static final String EMAIL_COLUMN =
            "EMAIL_COLUMN";
    public static final String PICTURE =
            "PICTURE";

    //TODO: Create public field for each column in your table.
    /***/


    // Database open/upgrade helper
    private DBOpenHelper DBOpenHelper;

    public Database(Context context) {
        DBOpenHelper = new DBOpenHelper(context, DBOpenHelper.DATABASE_NAME, null,
                DBOpenHelper.DATABASE_VERSION);
    }

    // Called when you no longer need access to the database.
    public void closeDatabase() {
        DBOpenHelper.close();
    }

    private Cursor getAccessibleAddress() {
        /**
         * Listing 8-3: Querying a database
         */
        // Specify the result column projection. Return the minimum set
        // of columns required to satisfy your requirements.
        String[] result_columns = new String[] {
                KEY_ID, ADDRESS_COLUMN, PHONE_COLUMN, EMAIL_COLUMN, PICTURE };

        // Specify the where clause that will limit our results.
        String where = ADDRESS_COLUMN + "=" + 1;

        // Replace these with valid SQL statements as necessary.
        String whereArgs[] = null;
        String groupBy = null;
        String having = null;
        String order = null;

        SQLiteDatabase db = DBOpenHelper.getWritableDatabase();
        Cursor cursor = db.query(DBOpenHelper.DATABASE_TABLE,
                result_columns, where,
                whereArgs, groupBy, having, order);
        //
        return cursor;
    }

    public float getAverageAccessibleAddressValue() {
        Cursor cursor = getAccessibleAddress();

        /**
         * Listing 8-4: Extracting values from a Cursor
         */
        float totalHoard = 0f;
        float averageHoard = 0f;

        // Find the index to the column(s) being used.
        int PHONE_COLUMN_INDEX =
                cursor.getColumnIndexOrThrow(PHONE_COLUMN);

        // Iterate over the cursors rows.
        // The Cursor is initialized at before first, so we can
        // check only if there is a "next" row available. If the
        // result Cursor is empty this will return false.
        while (cursor.moveToNext()) {
            float hoard = cursor.getFloat(PHONE_COLUMN_INDEX);
            totalHoard += hoard;
        }

        // Calculate an average -- checking for divide by zero errors.
        float cursorCount = cursor.getCount();
        averageHoard = cursorCount > 0 ?
                (totalHoard / cursorCount) : Float.NaN;

        // Close the Cursor when you've finished with it.
        cursor.close();

        return averageHoard;
    }

    public void addNewCustomer(String customerName, String addressValue, String phoneValue, String emailValue) {
        /**
         * Listing 8-5: Inserting new rows into a database
         */
        // Create a new row of values to insert.
        ContentValues newValues = new ContentValues();

        // Assign values for each row.
        newValues.put(KEY_ID, customerName);
        newValues.put(PHONE_COLUMN, phoneValue);
        newValues.put(ADDRESS_COLUMN, addressValue);
        newValues.put(EMAIL_COLUMN, emailValue);
       // newValues.put(PICTURE, pictureValue);
        // [ ... Repeat for each column / value pair ... ]

        // Insert the row into your table
        SQLiteDatabase db = DBOpenHelper.getWritableDatabase();
        db.insert(DBOpenHelper.DATABASE_TABLE, null, newValues);
    }

    public void updatePhoneValue(String customerName, float newPhoneValue) {
        /**
         * Listing 8-6: Updating a database row
         */
        // Create the updated row Content Values.
        ContentValues updatedValues = new ContentValues();

        // Assign values for each row.
        updatedValues.put(PHONE_COLUMN, newPhoneValue);
        // [ ... Repeat for each column to update ... ]

        // Specify a where clause the defines which rows should be
        // updated. Specify where arguments as necessary.
        String where = KEY_ID + "=" + customerName;
        String whereArgs[] = null;

        // Update the row with the specified index with the new values.
        SQLiteDatabase db = DBOpenHelper.getWritableDatabase();
        db.update(DBOpenHelper.DATABASE_TABLE, updatedValues,
                where, whereArgs);
    }

    public void deleteEmptyHoards() {
        /**
         * Listing 8-7: Deleting a database row
         */
        // Specify a where clause that determines which row(s) to delete.
        // Specify where arguments as necessary.
        String where = PHONE_COLUMN + "=" + 0;
        String whereArgs[] = null;

        // Delete the rows that match the where clause.
        SQLiteDatabase db = DBOpenHelper.getWritableDatabase();
        db.delete(DBOpenHelper.DATABASE_TABLE, where, whereArgs);
    }

    public String getName() {
        return KEY_ID_COLUMN;
    }

    public String getAddress() {
        return ADDRESS_COLUMN;
    }

    public String getPhone_number() {
        return PHONE_COLUMN;
    }

    public String getEmail_number() {
        return EMAIL_COLUMN;
    }


    /**
     * Listing 8-2: Implementing an SQLite Open Helper
     */
    private static class DBOpenHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "myDatabase.db";
        private static final String DATABASE_TABLE = "Customers";
        private static final int DATABASE_VERSION = 1;

        // SQL Statement to create a new database.
        private static final String DATABASE_CREATE = "create table " +
                DATABASE_TABLE + " (" + KEY_ID +
                " integer primary key autoincrement, " +
                KEY_ID_COLUMN + " text not null, " +
                PHONE_COLUMN + " float, " +
                ADDRESS_COLUMN + " String," +
                EMAIL_COLUMN + " String" +
                ");";

        public DBOpenHelper(Context context, String name,
                            CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        // Called when no database exists in disk and the helper class needs
        // to create a new one.
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        // Called when there is a database version mismatch meaning that
        // the version of the database on disk needs to be upgraded to
        // the current version.
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion) {
            // Log the version upgrade.
            Log.w("TaskDBAdapter", "Upgrading from version " +
                    oldVersion + " to " +
                    newVersion + ", which will destroy all old data");

            // Upgrade the existing database to conform to the new
            // version. Multiple previous versions can be handled by
            // comparing oldVersion and newVersion values.

            // The simplest case is to drop the old table and create a new one.
            db.execSQL(new StringBuilder().append("DROP TABLE IF IT EXISTS ").append(DATABASE_TABLE).toString());
            // Create a new one.
            onCreate(db);
        }
    }
}
