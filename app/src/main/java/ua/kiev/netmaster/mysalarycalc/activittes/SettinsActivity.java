package ua.kiev.netmaster.mysalarycalc.activittes;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import ua.kiev.netmaster.mysalarycalc.R;


public class SettinsActivity extends Activity implements View.OnClickListener {

    private Button onMain2, saveChanges, addDeleteEmp, toDBbt;
    private EditText dayConnPrice, eveningConnPrice, boxInstallPrice, opticInstallPrice, employeeName, emplRait;
    private TextView currentSettingsTV;
    private SharedPreferences settingsShPref, employeesShPref;
   // Set<String> employees;
    private String[] keysArr = {"dayConnPrice","eveningConnPrice", "boxInstallPrice","opticInstallPrice", "employee"};
    private String[] employeeNames = {"default0","default1","default2"};
    private ArrayList<Integer> employeeRates;
    private Double[] valuesArr;
    private  Map<String, ? > prefMap, emplMap;
    private ArrayList<String> emplNames;
    private StringBuilder sb;
    private RadioGroup radioGroup;
    private Intent intent;

    private final String logTag = "MyLOG";


    private void initViews(){
        Log.d(logTag, "initViews() starts");

        emplNames = new ArrayList<>();
        employeeRates = new ArrayList<>();


        valuesArr = new Double[4];

        radioGroup = (RadioGroup)findViewById(R.id.radioGroupSett);

        onMain2 = (Button) findViewById(R.id.onMain2);
        onMain2.setOnClickListener(this);
            saveChanges = (Button) findViewById(R.id.saveChanges);
            saveChanges.setOnClickListener(this);
        addDeleteEmp = (Button) findViewById(R.id.addDeleteEmp);
        addDeleteEmp.setOnClickListener(this);
            toDBbt = (Button)findViewById(R.id.toDBbt);
            toDBbt.setOnClickListener(this);


        currentSettingsTV = (TextView)findViewById(R.id.currentSettingsTV);
            currentSettingsTV.setMovementMethod(new ScrollingMovementMethod());

        dayConnPrice = (EditText)findViewById(R.id.dayConnPrice);
            eveningConnPrice = (EditText)findViewById(R.id.eveningConnPrice);
        boxInstallPrice = (EditText)findViewById(R.id.boxInstallPrice);
            opticInstallPrice = (EditText)findViewById(R.id.opticInslallPrice);
        employeeName = (EditText)findViewById(R.id.employee_name);
            emplRait = (EditText)findViewById(R.id.emplRait);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settins);
        Log.d(logTag, "onCreate() starts");

        //work with ShPrefs:
        settingsShPref = getSharedPreferences("settings", MODE_PRIVATE);
        employeesShPref = getSharedPreferences("employees", MODE_PRIVATE);

        initViews();

        loadSettings();

        setCurValToET();

        loadEmployees();

        monitor();
    }

    private void setCurValToET(){
        Log.d(logTag, "setCurValToET() starts");
        if(valuesArr[0]!=null) {

            dayConnPrice.setText(valuesArr[0].toString());
            eveningConnPrice.setText(valuesArr[1].toString());
            boxInstallPrice.setText(valuesArr[2].toString());
            opticInstallPrice.setText(valuesArr[3].toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settins, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveChanges(){
        Log.d(logTag, "saveChanges() starts");
        valuesArr[0] = Double.parseDouble(dayConnPrice.getText().toString());
        valuesArr[1] = Double.parseDouble(eveningConnPrice.getText().toString());
        valuesArr[2] = Double.parseDouble(boxInstallPrice.getText().toString());
        valuesArr[3] = Double.parseDouble(opticInstallPrice.getText().toString());

        SharedPreferences.Editor editor = settingsShPref.edit();

        for(int i = 0; i < keysArr.length-1;i++ ){
            editor.putString(keysArr[i], valuesArr[i].toString());
        }
        editor.commit();
        monitor();

    }

    private void monitor() {
        loadSettings();
        loadEmployees();

        Log.d(logTag, "monitor() starts");

        //Add info about settings;
        sb = new StringBuilder();
        sb.append("Текущие настройки: \n");
        for(int i=0; i<keysArr.length-1; i++ ){
            sb.append(keysArr[i]+" : "+valuesArr[i]+" UAH/Unit \n");
        }

        //Add info about employee;
        sb.append("\r\nСотрудники: \n");
        for (String name : emplMap.keySet()){
            sb.append(name +" - ставка: " +emplMap.get(name)+" грн.\n");
        }
        currentSettingsTV.setText(sb.toString());

    }

    private void loadEmployees(){
        Log.d(logTag, "loadEmployees() starts");
        emplMap= employeesShPref.getAll();
        if(!emplMap.isEmpty()) {
            emplNames.addAll(emplMap.keySet());
            employeeRates.addAll((Collection<Integer>) emplMap.values());
            //Check
            System.out.println(emplNames);
            System.out.println(employeeRates);
        }else {
            //if we have no employees -> creation of default employees and reload method:
            for(String name : employeeNames){
                saveEmployee(name, 0);
            }
            Toast.makeText(this,"Добавьте сотрудников в настройках приложения.", Toast.LENGTH_LONG).show();

            loadEmployees();
        }
    }

    private void saveEmployee(String name, Integer rait){
        Log.d(logTag, "saveEmployee() starts");
        SharedPreferences.Editor editor = employeesShPref.edit();
        editor.putInt(name, rait);
        editor.commit();
    }
    private void changeEmployee(String name, Integer rait){
        Log.d(logTag, "changeEmployee() starts");
        SharedPreferences.Editor editor = employeesShPref.edit();
        editor.remove(name);
        editor.putInt(name, rait);
        editor.commit();
    }

    private void deleteEmployee(String name){
        Log.d(logTag, "deleteEmployee() starts");
        SharedPreferences.Editor editor = employeesShPref.edit();
        editor.remove(name);
        editor.commit();
    }


    private void addDeleteEmp(){
        Log.d(logTag, "addDeleteEmp() starts");
        String emplName = employeeName.getText().toString();

        switch (radioGroup.getCheckedRadioButtonId()){
            case (R.id.rbCreate) :
                saveEmployee(emplName, Integer.parseInt(emplRait.getText().toString()));
                break;
            case (R.id.rbChange) :
                changeEmployee(emplName, Integer.parseInt(emplRait.getText().toString()));
                break;
            case (R.id.rbDelete) :
                deleteEmployee(emplName);
                break;
        }
    }

    private void loadSettings(){
        Log.d(logTag, "loadSettings() starts");
        sb = new StringBuilder();
        sb.append("Настройки: \n");
        prefMap = settingsShPref.getAll();
        if(!prefMap.isEmpty()){
            for(int i=0; i<keysArr.length-1;i++ ){
                valuesArr[i]= Double.parseDouble(prefMap.get(keysArr[i]).toString());
                sb.append(keysArr[i]+" : "+valuesArr[i]+"\n");
            }
        currentSettingsTV.setText(sb.toString());
        }else
            Toast.makeText(this,"Установите цены в настройках приложения", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onClick(View view) {
        Log.d(logTag, "onClick() starts");
        switch (view.getId()) {
            case R.id.saveChanges:
                saveChanges();
                monitor();
                break;
            case R.id.onMain2:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.addDeleteEmp:
                addDeleteEmp();
                monitor();
                break;
            case R.id.toDBbt :
                intent = new Intent(this, DataBaseAct.class);
                startActivity(intent);
                break;
        }

    }
}
