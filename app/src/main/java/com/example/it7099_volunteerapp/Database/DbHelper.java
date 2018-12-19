package com.example.it7099_volunteerapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.util.Log;

import  com.example.it7099_volunteerapp.JavaClasses.User;


import static com.example.it7099_volunteerapp.Database.VolunteerContractClass.UserContract.TABLE_USER;


public final class DbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "VolunteerApp.db";
        private static final int DATABASE_VERSION = 3;
        private static final String LOG = "DbHelper";


        private static final String SQL_CREATE_USER =
                "CREATE TABLE IF NOT EXISTS " + TABLE_USER + " (" +
                        VolunteerContractClass.UserContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                        VolunteerContractClass.UserContract.User_firstName + " TEXT," +
                        VolunteerContractClass.UserContract.User_lastName + " TEXT," +
                        VolunteerContractClass.UserContract.User_email + " TEXT," +
                        VolunteerContractClass.UserContract.User_password + " TEXT," +
                        VolunteerContractClass.UserContract.User_gender + " TEXT," +
                        VolunteerContractClass.UserContract.User_skills + " TEXT," +
                        VolunteerContractClass.UserContract.User_preference + " TEXT," +
                        VolunteerContractClass.UserContract.User_DOB + " TEXT," +
                        VolunteerContractClass.UserContract.User_userType + " TEXT DEFAULT 1 ," +
                        VolunteerContractClass.UserContract.User_education + " TEXT," +
                        VolunteerContractClass.UserContract.User_image + "BLOB)";


        private static final String SQL_CREATE_EVENT =
                "CREATE TABLE IF NOT EXISTS " + VolunteerContractClass.EventContract.TABLE_EVENT + " (" +
                        VolunteerContractClass.EventContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                        VolunteerContractClass.EventContract.Event_title + " TEXT," +
                        VolunteerContractClass.EventContract.Event_description + " TEXT," +
                        VolunteerContractClass.EventContract.Event_Condition + " TEXT," +
                        VolunteerContractClass.EventContract.Event_StartDate + " TEXT," +
                        VolunteerContractClass.EventContract.Event_StartTime + " TEXT," +
                        VolunteerContractClass.EventContract.Event_EndDate + " TEXT," +
                        VolunteerContractClass.EventContract.Event_EndTime + " TEXT," +
                        VolunteerContractClass.EventContract.Event_Location + " TEXT," +
                        VolunteerContractClass.EventContract.Event_Logo + " BLOB," +
                        VolunteerContractClass.EventCategoryContract.EventCat_Id + " INT" +
                        " organizationId INTEGER" +
                        " FOREIGN KEY(eventCategoryId) REFERENCES eventCategory(_id)," +
                        "FOREIGN KEY(organizationId) REFERENCE Organization(_id))";


        // EVENT CATEGORY TABLE
        private static final String SQL_CREATE_EVENT_CATEGORY =
                "CREATE TABLE IF NOT EXISTS " + VolunteerContractClass.EventCategoryContract.TABLE_CATEGORY + " ("
                        + VolunteerContractClass.EventCategoryContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        VolunteerContractClass.EventCategoryContract.EventCat_name + " TEXT)";


        // EVENT REVIEW TABLE
        private static final String SQL_CREATE_EVENT_REVIEW =
                "CREATE TABLE IF NOT EXISTS " + VolunteerContractClass.EventReviewContract.TABLE_EVENT_REVIEW + "(" +
                        VolunteerContractClass.EventReviewContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        VolunteerContractClass.EventReviewContract.EventRev_review + " TEXT," +
                        "userId INT" +
                        "activityId INT " +
                        "registrationId INT" +
                        " FOREIGN KEY (userId) REFERENCES User(_id)" +
                        " FOREIGN KEY (activityId) REFERENCES Activity(_id)" +
                        " FOREIGN KEY (registrationId) REFERENCES Registration(_id)";

        //FK  USER ID, ACTIVITY ID, REGISTRATION ID


        // EVENT REGISTRATION TABLE
        private static final String SQL_CREATE_REGISTRATION =
                "CREATE TABLE IF NOT EXISTS " + VolunteerContractClass.EventRegistrationContract.TABLE_EVENT_REGISTRATION + " (" +
                        VolunteerContractClass.EventRegistrationContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        VolunteerContractClass.EventRegistrationContract.EventReg_checkIn + " INTEGER" +
                        "userId INT" +
                        "activityId INT" +
                        "FOREIGN KEY (userId) REFERENCE User(_id)"
                        + "FOREIGN KEY (activityId) REFERENCE Activity(_id)";
        // FK
        // USER ID , ACTIVITY ID


// ORG TABLE
        // fk
        // organization id


        // org cat table


        // user feedback
        // given by the org to users
        // FK-
        //USER ID , ACTIVITY ID , REGISTRATION ID


        public DbHelper(Context context) {
            // constructor
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            Log.d("Database Operation", "Db created");
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_USER);
            // db.execSQL(SQL_CREATE_USER);
            // db.execSQL(SQL_CREATE_USER);
            // db.execSQL(SQL_CREATE_USER);
            // db.execSQL(SQL_CREATE_USER);
            // db.execSQL(SQL_CREATE_USER);
            // db.execSQL(SQL_CREATE_USER);
            // db.execSQL(SQL_CREATE_USER);
            //db.execSQL(SQL_CREATE_USER);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            //  db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);

        }


        public void addUser(User user) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(VolunteerContractClass.UserContract.User_firstName, user.getFirstName());
            values.put(VolunteerContractClass.UserContract.User_email, user.getEmail());
            values.put(VolunteerContractClass.UserContract.User_password, user.getPassword());

            // Inserting Row
            db.insert(TABLE_USER, null, values);
            db.close();

        }
        public void deleteUser(User user) {
            SQLiteDatabase db = this.getWritableDatabase();
            // delete user record by id
            db.delete(TABLE_USER, VolunteerContractClass.UserContract._ID + " = ?",
                    new String[]{String.valueOf(user.getUserId())});
            db.close();
        }

        /**
         * This method to check user exist or not
         *
         * @param email
         * @return true/false
         */
        public boolean checkUser(String email) {

            // array of columns to fetch
            String[] columns = {
                    VolunteerContractClass.UserContract._ID
            };
            SQLiteDatabase db = this.getReadableDatabase();

            // selection criteria
            String selection = VolunteerContractClass.UserContract.User_email + " = ?";

            // selection argument
            String[] selectionArgs = {email};

            // query user table with condition
            /**
             * Here query function is used to fetch records from user table this function works like we use sql query.
             * SQL query equivalent to this query function is
             * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
             */
            Cursor cursor = db.query(TABLE_USER, //Table to query
                    columns,                    //columns to return
                    selection,                  //columns for the WHERE clause
                    selectionArgs,              //The values for the WHERE clause
                    null,                       //group the rows
                    null,                      //filter by row groups
                    null);                      //The sort order
            int cursorCount = cursor.getCount();
            cursor.close();
            db.close();

            return cursorCount > 0;

        }

        /**
         * This method to check user exist or not
         *
         * @param email
         * @param password
         * @return true/false
         */
        public boolean checkUser(String email, String password) {

            // array of columns to fetch
            String[] columns = {
                    VolunteerContractClass.UserContract._ID
            };
            SQLiteDatabase db = this.getReadableDatabase();
            // selection criteria
            String selection = VolunteerContractClass.UserContract.User_email + " = ?" + " AND " + VolunteerContractClass.UserContract.User_password + " = ?";

            // selection arguments
            String[] selectionArgs = {email, password};

            // query user table with conditions
            /**
             * Here query function is used to fetch records from user table this function works like we use sql query.
             * SQL query equivalent to this query function is
             * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
             */
            Cursor cursor = db.query(TABLE_USER, //Table to query
                    columns,                    //columns to return
                    selection,                  //columns for the WHERE clause
                    selectionArgs,              //The values for the WHERE clause
                    null,                       //group the rows
                    null,                       //filter by row groups
                    null);                      //The sort order

            int cursorCount = cursor.getCount();

            cursor.close();
            db.close();
            return cursorCount > 0;

        }

    }
