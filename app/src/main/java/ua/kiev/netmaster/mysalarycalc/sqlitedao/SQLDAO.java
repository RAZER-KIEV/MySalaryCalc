package ua.kiev.netmaster.mysalarycalc.sqlitedao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import ua.kiev.netmaster.mysalarycalc.daopreff.DaoPreff;

/**
 * Created by ПК on 19.10.2015.
 */
public class SQLDAO extends SQLiteOpenHelper {

    static SQLDAO sqldao;

    static SQLDAO getInstance(Context context){
        if(sqldao==null){
            sqldao = new SQLDAO(context);
        }
        return sqldao;
    }

    private SQLDAO(Context context) {
        super(context, "myDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(DaoPreff.logTag, "--- onCreate database ---");
        //Salary tab;
        db.execSQL("create table Salary ("
                + "id integer primary key autoincrement,"
                + "emplId integer,"
                + "dayCon integer,"
                + "eveningCom integer,"
                + "cableInst integer,"
                + "eveningCom integer,"
                + "boxesInst integer,"
                + "otherTasks integer,"
                + "rate integer,"
                + "summary integer,"
                + "comment text,"
                + "email text);");

        //Post tab;
        db.execSQL("create table Posts ("
                + "id integer primary key autoincrement,"
                + "postName text,"
                + "comment text);");

        //Employee tab;
        db.execSQL("create table Employees ("
                + "id integer primary key autoincrement,"
                + "name text,"
                + "postId integer,"
                + "rate integer,"
                + "startDay text,"
                + "inn text,"
                + "email text,"
                + "phone text,"
                + "comment text);");

        //WorkTypePrice tab;
        db.execSQL("create table Employees ("
                + "id integer primary key autoincrement,"
                + "typeOfWork text,"
                + "price real,"
                + "comment text);");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
