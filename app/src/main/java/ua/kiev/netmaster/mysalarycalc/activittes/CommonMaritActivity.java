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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

import ua.kiev.netmaster.mysalarycalc.R;
import ua.kiev.netmaster.mysalarycalc.daopreff.DaoPreff;


public class CommonMaritActivity extends Activity implements View.OnClickListener {

    private int dayconn = 0;
    private int evenyngconn = 0;
    private int boxinst = 0;
    private int opticinst = 0;
    private int others =0;
    private double summgen = 0;
    private double sfeach = 0;
    private int emploeeq = 0;

    private Map<String, ? > prefMap;

    private Button btCalcStart, btNext;

    private CheckBox cbEmployee1, cbEmployee2, cbEmployee3, cbEmployee4, cbEmployee5, cbEmployee6,cbEmployee7,cbEmployee8,cbEmployee9;

    private EditText etDayConn, etEvenConn, etOptInst, etBxInst, etOthers;

    private TextView tvSummForEach, tvJournal;

    private StringBuilder sbJounal;

    private String logTag = "MyLOG";

    private SharedPreferences settingsShPref, employeesShPref;

    private String[] keysArr = {"dayConnPrice","eveningConnPrice", "boxInstallPrice","opticInstallPrice", "employee"};

    private Double [] workprices;

    private boolean[] employeeIsChecked;

    private DaoPreff daoPreff;

    private ArrayList<CheckBox> checkBoxArrayList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newcommonmarit);
        Log.d(logTag, "CommonMaritActivity. onCreate() started");

        //initialize Views
        initViews();

        //Loading settings:
        loadSettings();

        //daoPreff.loadSettings();
        daoPreff.loadEmployees();

        prepareCheckBoxes();
    }

    private void initViews(){
        Log.d(logTag, "CommonMaritActivity. initViews() started");
        checkBoxArrayList = new ArrayList<>();
        workprices = new Double[4];

        btCalcStart = (Button) findViewById(R.id.btCalcStart);
        btNext = (Button) findViewById(R.id.btOnMain);

        cbEmployee1 = (CheckBox) findViewById(R.id.cbEmployee1);
        cbEmployee2 = (CheckBox) findViewById(R.id.cbEmployee2);
        cbEmployee3 = (CheckBox) findViewById(R.id.cbEmployee3);
        cbEmployee4 = (CheckBox) findViewById(R.id.cbEmployee4);
        cbEmployee5 = (CheckBox) findViewById(R.id.cbEmployee5);
        cbEmployee6 = (CheckBox) findViewById(R.id.cbEmployee6);
        cbEmployee7 = (CheckBox) findViewById(R.id.cbEmployee7);
        cbEmployee8 = (CheckBox) findViewById(R.id.cbEmployee8);
        cbEmployee9 = (CheckBox) findViewById(R.id.cbEmployee9);

        checkBoxArrayList.add(cbEmployee1);
        checkBoxArrayList.add(cbEmployee2);
        checkBoxArrayList.add(cbEmployee3);
        checkBoxArrayList.add(cbEmployee4);
        checkBoxArrayList.add(cbEmployee5);
        checkBoxArrayList.add(cbEmployee6);
        checkBoxArrayList.add(cbEmployee7);
        checkBoxArrayList.add(cbEmployee8);
        checkBoxArrayList.add(cbEmployee9);


        etDayConn = (EditText) findViewById(R.id.etDayConn);
        etEvenConn = (EditText) findViewById(R.id.etEvenConn);
        etOptInst = (EditText) findViewById(R.id.etOptInst);
        etBxInst = (EditText) findViewById(R.id.etBxInst);
        etOthers = (EditText)findViewById(R.id.etOthers);

        tvSummForEach = (TextView) findViewById(R.id.tvSummForEach);
        tvJournal = (TextView) findViewById(R.id.tvJournal);
        tvJournal.setMovementMethod(new ScrollingMovementMethod());


        btCalcStart.setOnClickListener(this);
        btNext.setOnClickListener(this);

        settingsShPref = getSharedPreferences("settings", MODE_PRIVATE);
        employeesShPref = getSharedPreferences("employees", MODE_PRIVATE);

        daoPreff = DaoPreff.getDaoPreff(settingsShPref, employeesShPref);

        employeeIsChecked = new boolean[9];

    }
    private void prepareCheckBoxes(){
        Log.d(logTag, "CommonMaritActivity. prepareCheckBoxes() started");
          for (int i = 0; i < daoPreff.emplNames.size(); i++) {
                checkBoxArrayList.get(i).setText(daoPreff.emplNames.get(i));
                checkBoxArrayList.get(i).setVisibility(View.VISIBLE);
          }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private void loadSettings() {
        Log.d(logTag, "CommonMaritActivity. loadSettings() starts");
        prefMap = settingsShPref.getAll();
        if(!prefMap.isEmpty()){
            for (int i = 0; i < keysArr.length - 1; i++) {
            workprices[i] = Double.parseDouble(prefMap.get(keysArr[i]).toString());
            }
        }else
            Toast.makeText(this,"Стоимости оплачиваемых работ не установлены!", Toast.LENGTH_LONG).show();

    }

    private void makeMessage(){
        Log.d(logTag, "CommonMaritActivity. makeMessage() starts");

        sbJounal = new StringBuilder();
        sbJounal.append("Детализация:");
        sbJounal.append("\r\n Дневных подключений: " + dayconn + "/"+emploeeq+" на " + dayconn * workprices[0]/emploeeq + " грн."); //вечерних подключений" + evenyngconn);
        sbJounal.append("\r\n Вечерних подключений: " + evenyngconn + "/"+emploeeq+" на " + evenyngconn * workprices[1]/emploeeq + " грн.");
        sbJounal.append("\r\n Оптики протянуто: " + opticinst + "/"+emploeeq+" м. на " + opticinst * workprices[3]/emploeeq + " грн.");
        sbJounal.append("\r\n Ящиков установлено: " + boxinst + "/"+emploeeq+" шт. на " + boxinst * workprices[2]/emploeeq + " грн.");
        sbJounal.append("\r\n Других работ на : " + others + "/"+emploeeq+" грн. По "+others/emploeeq+" грн. каждому.");
        // sbJounal.append("\r\n Суммарно на: " + summgen/ + " грн.");
        sbJounal.append("\r\n Суммарно каждому по: " + sfeach + " грн.");
        tvJournal.setText(sbJounal.toString());
        tvSummForEach.setText(String.valueOf(sfeach));

    }


    private void calc(){
        Log.d(logTag, "CommonMaritActivity. calc() starts");

        if(daoPreff.emplCurSalaryMap==null){
            daoPreff.init();
        }
        dayconn = Integer.parseInt(etDayConn.getText().toString());
        evenyngconn = Integer.parseInt(etEvenConn.getText().toString());
        opticinst = Integer.parseInt(etOptInst.getText().toString());
        boxinst = Integer.parseInt(etBxInst.getText().toString());
        others = Integer.parseInt(etOthers.getText().toString());

        summgen = (dayconn * workprices[0] + evenyngconn * workprices[1] + boxinst * workprices[2] + opticinst * workprices[3] + others);
        sfeach = summgen / emploeeq;



        for(int i = 0; i<employeeIsChecked.length; i++) {
            System.out.println("i = " + i);
            if (employeeIsChecked[i]) {
                Integer  curSal = (int) Math.round(sfeach);
                System.out.println("daoPreff.emplCurSalaryMap==null : "+(daoPreff.emplCurSalaryMap==null));
                curSal +=(int)Math.round(daoPreff.emplCurSalaryMap.get(checkBoxArrayList.get(i).getText().toString()));
                daoPreff.emplCurSalaryMap.put(checkBoxArrayList.get(i).getText().toString(), curSal.doubleValue());
            }
        }
    }


    @Override
    public void onClick(View v) {
        Log.d(logTag, "CommonMaritActivity. onClick() starts");
        switch (v.getId()) {
            case R.id.btCalcStart:
                emploeeq = 0;
                int i=0;
                for (CheckBox checkBox: checkBoxArrayList){
                    employeeIsChecked[i]=checkBox.isChecked();
                    if(employeeIsChecked[i]) emploeeq++;
                    i++;
                }
                Log.d(logTag, String.valueOf(emploeeq));

                Intent intent = new Intent();

                try {
                    calc();
                    makeMessage();
                    Toast.makeText(this, "Сохранено. Проделате это со всеми сотрудниками!", Toast.LENGTH_LONG).show();
                    setResult(RESULT_OK, intent);

                } catch (Exception e) {
                    System.out.println(e);
                    e.printStackTrace();
                    Toast.makeText(this, "Не используйте пробелы, выберите сотрудников и проверьте настройки цен!", Toast.LENGTH_LONG).show();
                    Log.d(logTag, "Catch Error");
                    tvJournal.setText(String.valueOf("Не используйте пробелы, выберите сотрудников и проверьте настройки цен!"));
                    tvSummForEach.setText(String.valueOf(sfeach));
                    setResult(RESULT_CANCELED, intent);

                }
                break;
            case R.id.btOnMain:
                finish();
                 break;


        }

    }
}