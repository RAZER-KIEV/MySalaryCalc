package ua.kiev.netmaster.mysalarycalc.daopreff;

import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ПК on 03.10.2015.
 */
public class DaoPreff {
    public SharedPreferences settingsShPref, employeesShPref;
    public String[] keysArr = {"dayConnPrice","eveningConnPrice", "boxInstallPrice","opticInstallPrice", "employee"};
    public String[] employeeDefaultNames = {"employee0","employee1","employee2"};
    public static ArrayList<Integer> employeeRates;
    public Double[] currentSettingsArr;
    public Map<String, String > prefMap;
    public Map<String, Integer> emplMap;// emplCurSalaryMap;
    public Map<String, Double > emplCurSalaryMap;
    public ArrayList<String> emplNames;
    public StringBuilder sb;
    public static final String logTag = "MyLOG";
    private static DaoPreff daoPreff = null;

    public static DaoPreff getDaoPreff(SharedPreferences settingsShPref, SharedPreferences employeesShPref){
        Log.d(logTag, "daoPreff==null: "+(daoPreff==null));
        if(daoPreff==null){
            daoPreff = new DaoPreff(settingsShPref,employeesShPref);
        }
        Log.d(logTag, "daoPreff==null, after creation: " + (daoPreff == null));
        return daoPreff;
    }

    private DaoPreff( SharedPreferences settingsShPref, SharedPreferences employeesShPref) {
        this.settingsShPref = settingsShPref;
        this.employeesShPref = employeesShPref;

        init();

    }


    public void init(){
        Log.d(logTag, "DAO: init() starts");
        loadEmployees();

        if (!emplMap.isEmpty()) {
            //System.out.println("emplCurSalaryMap==null  "+(emplCurSalaryMap==null));
            emplCurSalaryMap = new HashMap<>();
            for (String name : emplMap.keySet()) {
                emplCurSalaryMap.put(name, 0.0);
            }
        } else {
        //if we have no employees -> creation of default employees and reload method:
        for(String name : employeeDefaultNames){
            saveEmployee(name, 0);
        }
        loadEmployees();
    }

        loadSettings();
        Log.d(logTag, "DAO: DaoPreff object created!");
    }

    public void loadEmployees(){
        Log.d(logTag, "DAO: loadEmployees() starts");
        emplNames = new ArrayList<>();
        employeeRates = new ArrayList<>();
        emplMap = (Map<String, Integer>) employeesShPref.getAll();



            emplNames.addAll(emplMap.keySet());
            //// TODO: 06.10.2015  
            employeeRates.addAll(emplMap.values());
            //Check
            System.out.println(emplNames);
            System.out.println(employeeRates);

    }
    public void loadSettings() {
        Log.d(logTag, "DAO: loadSettings() starts");
        sb = new StringBuilder();
        sb.append("Current Settings: \n");
        prefMap = (Map<String, String>) settingsShPref.getAll();
        currentSettingsArr = new Double[prefMap.size()];
        if (!prefMap.isEmpty()) {
            for (int i = 0; i < keysArr.length - 1; i++) {
                currentSettingsArr[i] = Double.parseDouble(prefMap.get(keysArr[i]));
                sb.append(keysArr[i] + " : " + currentSettingsArr[i] + "\n");
            }
        }
    }

    public void saveEmployee(String name, int rait){
        Log.d(logTag, "saveEmployee() starts");
        SharedPreferences.Editor editor = employeesShPref.edit();
        editor.putInt(name, rait);
        editor.commit();
    }
    public void changeEmployee(String name, String rait){
        Log.d(logTag, "changeEmployee() starts");
        SharedPreferences.Editor editor = employeesShPref.edit();
        editor.remove(name);
        editor.putString(name, rait);
        editor.commit();
    }

    public void deleteEmployee(String name){
        Log.d(logTag, "deleteEmployee() starts");
        SharedPreferences.Editor editor = employeesShPref.edit();
        editor.remove(name);
        editor.commit();
    }




}
