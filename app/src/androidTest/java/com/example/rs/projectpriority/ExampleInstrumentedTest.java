package com.example.rs.projectpriority;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.content.ContextCompat;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.nio.file.ProviderNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    public Context context(){
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.rs.projectpriority", appContext.getPackageName());
        return appContext;
    }

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.rs.projectpriority", appContext.getPackageName());
    }

    @Test
    public void testWriteCalendarPermission(){
        int permissionCheck = ContextCompat.checkSelfPermission(context(),
                Manifest.permission.WRITE_CALENDAR);

        assertEquals(PackageManager.PERMISSION_GRANTED, permissionCheck);
    }

    @Test
    public void testProjectClassEmptyConstructor(){
        Project project = new Project();
        assertEquals(project.getId(), null);
        assertEquals(project.getName(), "New Project");
        assertEquals(project.getProgress(), 0);
        assertEquals(project.getToDoList(), false);
    }

    @Test
    public void testDB_projectsHelper_getAllProjects(){
        ProjectsTableHelper dbHelper = new ProjectsTableHelper(context(), true);

        ArrayList<Project> projArray = new ArrayList<>();
        for(int i = 0; i<10; i++){
            Project temp = new Project();
            temp.setToDoList(true);
            temp.setDeadline("11/22/2017");
            temp.setProgress(100);
            temp.setName("Project"+i);
            projArray.add(temp);
        }

        for(Project proj : projArray){
            dbHelper.newProject(proj);
        }

        HashMap<String, Integer> projects = new HashMap<>();
        projects = dbHelper.getAllProjects();

        assertEquals(projects.size(), 10);



    }

    @Test
    public void testDateConversion(){

        Project project = new Project();

        project.setDeadline("11/11/2017");
        assertEquals(project.getDeadline_asString(),"11/11/2017");
        assertArrayEquals(new int[]{11,11,2017},project.getDeadline_asIntAR());

    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


}
