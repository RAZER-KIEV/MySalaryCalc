package ua.kiev.netmaster.mysalarycalc.sqlitedao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ua.kiev.netmaster.mysalarycalc.domain.Employee;

/**
 * Created by ПК on 24.10.2015.
 */
public class DaoEmployeeImpl implements DaoEmployee{
    private SQLiteDatabase database;
    private SQLDAO sqldao;
    private ContentValues contentValues;
    private Map<String, String> oneEmplprepareMap;
    private Employee employee;

    public DaoEmployeeImpl(Context context) {
        sqldao = SQLDAO.getInstance(context);
        database = sqldao.getWritableDatabase();
        oneEmplprepareMap = new HashMap<>();

    }



    @Override
    public Integer create(Employee employee) {
        contentValues = new ContentValues();
        contentValues.put("name", employee.getName());
        contentValues.put("postId", employee.getPost().getId());
        contentValues.put("rate", employee.getRate());
        contentValues.put("startDay", employee.getStartDay());
        contentValues.put("inn", employee.getInn());
        contentValues.put("email", employee.getEmail());
        contentValues.put("phone", employee.getPhone());
        contentValues.put("comment", employee.getComment());

        Integer rowId = (int) database.insert("Employees",null,contentValues);

        return rowId;
    }

    @Override
    public Employee read(Long id) {
        Cursor cursor = database.query("Employees", null,"id=?",new String[]{id.toString()},null,null,null);
        if(cursor!=null||cursor.moveToFirst()) {
        for(String colName: cursor.getColumnNames()){
            oneEmplprepareMap.put(colName, cursor.getString(cursor.getColumnIndex(colName)));
        }
           //employee = new Employee(Integer.parseInt(oneEmplprepareMap.get("id")),oneEmplprepareMap.get("name"),Integer.parseInt(oneEmplprepareMap.get("postId")), )
        }


        return null;
    }

    @Override
    public boolean update(Employee employee) {
        return false;
    }

    @Override
    public boolean delete(Employee employee) {
        return false;
    }

    @Override
    public List<Employee> getAll() {
        return null;
    }
}
