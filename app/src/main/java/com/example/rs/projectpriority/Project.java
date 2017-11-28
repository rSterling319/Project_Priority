package com.example.rs.projectpriority;

/**
 * Created by rs on 11/16/17.
 */

public class Project {

    private Integer id;
    private String name;
    private int progress;
    private String deadline;
    private Boolean onToDoList;

    Project(){
        id = null;
        name="New Project";
        progress = 0;
        onToDoList = false;
    };
    Project(String _name){
        name = _name;
    }
    Project(Integer _id, String _name, int _progress, String _deadline, Boolean _onToDoList){
        id = _id;
        name = _name;
        progress = _progress;
        deadline = _deadline;
        onToDoList = _onToDoList;
    }

    public void setId(Integer _id){id=_id;}
    public void setName(String _name){
        name = _name;
    }
    public void setProgress(int _progress){
        progress = _progress;
    }
    public void setDeadline(String _deadline){deadline=_deadline;}
    public void setToDoList(Boolean _onToDoList){onToDoList =_onToDoList;}

    public Integer getId(){return id;}
    public String getName(){
        return name;
    }
    public int getProgress(){
        return progress;
    }
    public int[] getDeadline_asIntAR(){
        int[]m_d_y = new int[3];
        String[] splitDeadline = deadline.split("/");
        for(int i = 0; i<3; ++i){
            m_d_y[i]=Integer.parseInt(splitDeadline[i]);
        }
        return m_d_y;}
    public String getDeadline_asString(){return deadline;}

    public boolean getToDoList() {
        return onToDoList;
    }
}
