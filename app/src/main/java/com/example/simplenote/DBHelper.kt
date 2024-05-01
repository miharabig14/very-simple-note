package com.example.simplenote

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

@SuppressLint("Range")
class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    // below is the method for creating a database by a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        // below is a sqlite query, where column names
        // along with their data types is given
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                TITLE_COl + " TEXT," +
                CONTENT_COl + " TEXT" + ")")

        // we are calling sqlite
        // method for executing our query
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    // This method is for adding data in our database
    fun insertData(article: Article){

        // below we are creating
        // a content values variable
        val values = ContentValues()

        // we are inserting our values
        // in the form of key-value pair
        values.put(TITLE_COl, article.title)
        values.put(CONTENT_COl, article.content)

        // here we are creating a
        // writable variable of
        // our database as we want to
        // insert value in our database
        val db = this.writableDatabase

        // all values are inserted into database
        val result = db.insert(TABLE_NAME, null, values)
        // at last we are
        // closing our database
        db.close()
    }

    // below method is to get
    // all data from our database
    fun getArticleById(id: Int): Article? {
        val db = this.readableDatabase

        // below code returns a cursor to
        // read data from the database
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE id=$id", null)
        if(cursor.moveToFirst()) {
            return Article(
                cursor.getString(cursor.getColumnIndexOrThrow(TITLE_COl)),
                cursor.getString(cursor.getColumnIndexOrThrow(CONTENT_COl)),
                cursor.getString(cursor.getColumnIndexOrThrow(ID_COL)).toInt(),
            )
        }
        return null
    }
    fun deleteArticleById(id: Int) {
        val db = this.writableDatabase
        db.execSQL("DELETE FROM $TABLE_NAME WHERE id=$id")
    }

    fun readData(): ArrayList<Article> {
        val articles = ArrayList<Article>()
        // here we are creating a readable
        // variable of our database
        // as we want to read value from it
        val db = this.readableDatabase

        // below code returns a cursor to
        // read data from the database
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        if(cursor.moveToFirst()) {
            do {
                articles.add(
                    Article(
                        cursor.getString(cursor.getColumnIndexOrThrow(TITLE_COl)),
                        cursor.getString(cursor.getColumnIndexOrThrow(CONTENT_COl)),
                        cursor.getString(cursor.getColumnIndexOrThrow(ID_COL)).toInt(),
                    )
                )
            } while (cursor.moveToNext())
        }
        return articles
    }

    companion object{
        // here we have defined variables for our database

        // below is variable for database name
        private val DATABASE_NAME = "articles"

        // below is the variable for database version
        private val DATABASE_VERSION = 1

        // below is the variable for table name
        val TABLE_NAME = "gfg_table"

        // below is the variable for id column
        val ID_COL = "id"

        // below is the variable for name column
        val TITLE_COl = "name"

        // below is the variable for age column
        val CONTENT_COl = "age"
    }
}
