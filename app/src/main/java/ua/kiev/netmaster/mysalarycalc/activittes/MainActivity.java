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
import android.widget.TextView;
import android.widget.Toast;

import ua.kiev.netmaster.mysalarycalc.R;
import ua.kiev.netmaster.mysalarycalc.daopreff.DaoPreff;


public class MainActivity extends Activity implements View.OnClickListener {

    private Button btSettings, btCommonMarit;
    private TextView tvMainTitle, tvMainMonitor;
    private StringBuilder sbMonitor;
    private final String logTag = "MyLOG";
    private static DaoPreff daoPreff;
    private SharedPreferences settingsShPref, employeesShPref;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.d(logTag, "MainActivity. onCreate() started");



        initAll();

        monitor();

    }

    private void initAll(){
        Log.d(logTag, "MainActivity .initAll() started");

        settingsShPref = getSharedPreferences("settings", MODE_PRIVATE);
        employeesShPref = getSharedPreferences("employees", MODE_PRIVATE);

        daoPreff = DaoPreff.getDaoPreff(settingsShPref,employeesShPref);

        //emploeeSalaryArray = new ArrayList<>();
        //emploeeSalaryArrayBuffer = new ArrayList<>();
        btSettings = (Button)findViewById(R.id.btSettings);
        btSettings.setOnClickListener(this);

        btCommonMarit = (Button)findViewById(R.id.btCommonMerit);
        btCommonMarit.setOnClickListener(this);


        tvMainTitle = (TextView)findViewById(R.id.tvMainTitle);
        tvMainMonitor = (TextView) findViewById(R.id.tvMainMonitor);
        tvMainMonitor.setMovementMethod(new ScrollingMovementMethod());

        sbMonitor = new StringBuilder();
    }

    private void monitor(){
        daoPreff.loadEmployees();
        Log.d(logTag, "MainActivity .monitor() started");
        sbMonitor.delete(0,sbMonitor.length());
        int i = 0;
        for (String emplName :daoPreff.emplNames) {
            System.out.println("emplName  "+emplName+"\n");
            System.out.println("daoPreff.emplNames.size() - " +daoPreff.emplNames.size());
            System.out.println("daoPreff.employeeRates == null:   " + (daoPreff.employeeRates == null));
            System.out.println("daoPreff.emplCurSalaryMap  == null: " + (daoPreff.emplCurSalaryMap == null));
            if(daoPreff.emplCurSalaryMap != null) {
                sbMonitor.append(emplName + "  \n" +
                        "  Ставка:                               " + daoPreff.employeeRates.get(i) + " грн.\n");
                sbMonitor.append("  Выполненные работы:   "+Math.round(daoPreff.emplCurSalaryMap.get(emplName)) + " грн.\n");
                sbMonitor.append("  Суммарно на:                   " + (int)(daoPreff.employeeRates.get(i) + daoPreff.emplCurSalaryMap.get(emplName)) + " грн.  \n\n");
            }
            i++;
        }
        tvMainMonitor.setText(sbMonitor);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
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

    @Override
    public void onClick(View view) {
        Log.d(logTag, "MainActivity .onClick() started");
        switch (view.getId()){
            case R.id.btCommonMerit:
                Intent intent = new Intent(this, CommonMaritActivity.class);
                startActivityForResult(intent, 1);
                break;

            case R.id.btSettings:
                Intent intent2 = new Intent(this, SettinsActivity.class);
                startActivityForResult(intent2, 3);
                break;

        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(logTag, "MainActivity .onActivityResult() started");
        if (resultCode == RESULT_OK){
                    Toast.makeText(this, "Значения зарплат увеличено. Считайте дальше", Toast.LENGTH_LONG).show();
            }
        daoPreff.loadEmployees();
        monitor();
    }
}
