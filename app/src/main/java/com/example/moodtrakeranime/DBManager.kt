package com.example.moodtrakeranime

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteStatement


/**
 * Класс, через который мы общаемся с нашей базой настроений
 */
class DBManager private constructor(context: Context) {
    private val db: SQLiteDatabase
    /**
     * Запрос на вставку нового элемента
     */
    private val insert: SQLiteStatement
    /**
     * Запрос на обновление позиции элемента
     */
    private val update: SQLiteStatement

    companion object {
        private var instance: DBManager? = null
        fun getInstance(context: Context): DBManager { /*
		 * Если не существует экземпляра, то создаём новый, иначе возвращаем
		 * лежащий у нас.
		 */
            if (instance == null) instance = DBManager(context)
            return instance!!
        }
    }

    fun insert(mood: Mood) : Long {
        insert.bindLong(1, mood.mood.toLong())
        insert.bindDouble(2, mood.latitude)
        insert.bindDouble(3, mood.latitude)
        insert.bindLong(4,mood.timestamp)
        return  insert.executeInsert()
    }

    fun update(mood: Mood, id: Long) {
        update.bindLong(1, mood.mood.toLong())
        update.bindDouble(2, mood.latitude)
        update.bindDouble(3, mood.latitude)
        update.bindLong(4,mood.timestamp)
        update.bindLong(5, id)
        return  update.execute()
    }

    fun moods():Int {
        val cursor = db.rawQuery("select count() from Moods", null)
        cursor.moveToFirst()
        return cursor.getInt(0)
    }

    fun moods(mood:Int):Int {
        val cursor = db.rawQuery("select count() from Moods where mood is ?", arrayOf(mood.toString()))
        cursor.moveToFirst()
        return cursor.getInt(0)
    }

    init { // Открываем или создаём нашу базу
        db = context.openOrCreateDatabase("mood.sqlite", Context.MODE_PRIVATE, null)
        // Важно иметь _id, иначе не будут работать CursorAdapter-ы
        db.execSQL(
            "create table if not exists Moods (" + "_id integer primary key," + "mood integer,"
                    + "latitude real," + "longitude real," + "created_at integer" + ");"
        )
        // Заодно с этим создадим наши шаблоны запросов вставки и обновления
        insert = db.compileStatement(
            "insert into Moods " + "(mood, latitude, longitude, created_at) values (?, ?, ?, ?);"
        )
        update = db.compileStatement(
            "update Moods set " + "mood=?, latitude=?, longitude=?, created_at=? " + "where _id is ?"
        )
    }
}
