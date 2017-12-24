package com.example.rs.projectpriority;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by rs on 11/15/17.
 */

public class ProjectActivity extends AppCompatActivity {
    ProjectsTableHelper dbHelper;

    private EditText projName=null;
    //seekbar
    private static SeekBar seekBar;
    private static TextView seekBarProgress;
    //date picker
    private static DatePicker datePicker;
    private static Calendar calendar;
    private static Button dateButton;
    int [] dateAsInt; // month, day, year

    //to-do switch
    private TextView switchText;
    private Switch aSwitch;
    //Toggles
    private LinearLayout detail_toggle;
    private ImageView detail_img_toggle;
    private LinearLayout cost_toggle;
    private ImageView cost_img_toggle;
    private LinearLayout material_toggle;
    private ImageView material_img_toggle;
    private LinearLayout subTask_toggle;
    private ImageView subTask_img_toggle;

    private String TAG = "ProjectActivity";


    public Project project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
    }

    @Override
    protected void onResume(){
        super.onResume();
        dbHelper = new ProjectsTableHelper(this, false);

        dateButton = (Button) findViewById(R.id.dateButton);
        calendar = Calendar.getInstance();

        cost_toggle = (LinearLayout) findViewById(R.id.cost_toggle_clk);
        cost_toggle.setVisibility(View.GONE);
        cost_img_toggle =(ImageView) findViewById(R.id.costImgToggle);
        cost_img_toggle.setImageResource(R.mipmap.ic_add_black_24dp);
        material_toggle = (LinearLayout) findViewById(R.id.materials_toggle_clk);
        material_toggle.setVisibility(View.GONE);
        material_img_toggle =(ImageView) findViewById(R.id.material_tog_img);
        material_img_toggle. setImageResource(R.mipmap.ic_add_black_24dp);
        subTask_toggle = (LinearLayout) findViewById(R.id.subTask_drop);
        subTask_toggle.setVisibility(View.GONE);
        subTask_img_toggle = (ImageView) findViewById(R.id.subTask_tog_img);
        subTask_img_toggle.setImageResource(R.mipmap.ic_add_black_24dp);
        detail_toggle = (LinearLayout) findViewById(R.id.details_drop);
        detail_toggle.setVisibility(View.GONE);
        detail_img_toggle = (ImageView) findViewById(R.id.detailImgToggle);
        detail_img_toggle.setImageResource(R.mipmap.ic_add_black_24dp);


        aSwitch = (Switch) findViewById(R.id.toDoSwitch);
        switchText = (TextView) findViewById(R.id.switchText);

        projectSeekBar();
        projectNameText();
        projectOnToDoList();

        Intent intent = getIntent();
        int id = intent.getIntExtra("projectID", -1);
        Log.e(TAG,"Id = " + id);

        if(id == -1) {
            project = new Project();
            Log.e(TAG, "project ID = " + project.getId());
            setTitle(project.getName());
            showDate(calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.YEAR));
            setSwitch(project.getToDoList());
        }
        else{
            project = dbHelper.getProject((Integer) id);
            setTitle(project.getName());
            projName.setText(project.getName());
            seekBar.setProgress(project.getProgress());
            dateAsInt = project.getDeadline_asIntAR();
            showDate(dateAsInt[0],dateAsInt[1],dateAsInt[2]);
            setSwitch(project.getToDoList());

        }


    }

    public void detail_toggle_contents(View v){
        detail_toggle.setVisibility(detail_toggle.isShown() ? View.GONE : View.VISIBLE);
        detail_img_toggle.setImageResource(detail_toggle.isShown() ? R.mipmap.ic_remove_black_24dp : R.mipmap.ic_add_black_24dp);
    }

    public void cost_toggle_contents(View v){
        cost_toggle.setVisibility(cost_toggle.isShown() ? View.GONE : View.VISIBLE);
        cost_img_toggle.setImageResource(cost_toggle.isShown() ? R.mipmap.ic_remove_black_24dp : R.mipmap.ic_add_black_24dp);
    }

    public void materials_toggle_contents(View v){
        material_toggle.setVisibility(material_toggle.isShown() ? View.GONE : View.VISIBLE);
        material_img_toggle.setImageResource(material_toggle.isShown() ? R.mipmap.ic_remove_black_24dp : R.mipmap.ic_add_black_24dp);
    }

    public void sub_tasks_toggle_contents(View v){
        subTask_toggle.setVisibility(subTask_toggle.isShown() ? View.GONE : View.VISIBLE);
        subTask_img_toggle.setImageResource(subTask_toggle.isShown() ? R.mipmap.ic_remove_black_24dp : R.mipmap.ic_add_black_24dp);
    }

    public void projectNameText(){
        projName = (EditText) findViewById(R.id.projectName);
        final String projectName = projName.getText().toString();
        setTitle(projectName);

        projName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                setTitle(s);
                project.setName(s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setTitle(s);
                project.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                setTitle(s);
            }
        });
    }

    public void projectSeekBar(){
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBarProgress = (TextView) findViewById(R.id.seekBarProgress);
        seekBarProgress.setText("Not Started");


        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int progressValue;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progressValue = progress;
                        if(progressValue < 15){
                        seekBarProgress.setText("Not Started");
                        }
                        else if(progressValue<35){
                            seekBarProgress.setText("25% Done");
                        }
                        else if(progressValue<65){
                            seekBarProgress.setText("50% Done");
                        }
                        else if(progressValue<90){
                            seekBarProgress.setText("75% Done");
                        }
                        else{
                            seekBarProgress.setText("Complete!");
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        if(progressValue < 15){
                            seekBarProgress.setText("Not Started");
                            seekBar.setProgress(0);
                            project.setProgress(0);
                        }
                        else if(progressValue<35){
                            seekBarProgress.setText("25% Done");
                            seekBar.setProgress(25);
                            project.setProgress(25);
                        }
                        else if(progressValue<65){
                            seekBarProgress.setText("50% Done");
                            seekBar.setProgress(50);
                            project.setProgress(50);
                        }
                        else if(progressValue<90){
                            seekBarProgress.setText("75% Done");
                            seekBar.setProgress(75);
                            project.setProgress(75);
                        }
                        else{
                            seekBarProgress.setText("Complete!");
                            seekBar.setProgress(seekBar.getMax());
                            project.setProgress(seekBar.getMax());
                        }

                    }
                }
        );
    }

    public void projectOnToDoList(){

        aSwitch.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener(){
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                        setSwitch(isChecked);
                        Log.e(TAG, "Switch: " + isChecked);
                    }
                });
    }

    public void setSwitch(Boolean onToDoList){
        if(onToDoList){
            switchText.setText("yup");
        }
        else{
            switchText.setText("nope");
        }
        project.setToDoList(onToDoList);
        aSwitch.setChecked(onToDoList);
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view){
        showDialog(999);
//        Toast.makeText(getApplicationContext(),"ca",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Dialog onCreateDialog(int id){
        if(id==999){
            dateAsInt = project.getDeadline_asIntAR();
//            Log.e(TAG, "Date As Int = "+ dateAsInt[0]+ dateAsInt[1]+ dateAsInt[2]);
            return new DatePickerDialog(this,myDateListener, dateAsInt[2], dateAsInt[0]-1, dateAsInt[1]);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int _year, int _month, int _day) {
//            Log.e(TAG, "MyDateListener args = " +_month+_day+_year);
            showDate(_month+1,_day,_year);
        }
    };

    private void showDate(int _month, int _day, int _year){
        String dateText = _month+"/"+_day+"/"+_year;
//        Log.e(TAG,"DATE TEXT = " + dateText);
        project.setDeadline(dateText);
        dateButton.setText(project.getDeadline_asString());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.toDoList_menu:
                Intent intent = new Intent(this,DisplayToDoListActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    protected void onPause(){
        super.onPause();
        if(project.getId()==null){
            dbHelper.newProject(project);
        }else {
            dbHelper.updateProject(project);
        }
        dbHelper.close();
    }

}
