package com.example.rs.projectpriority;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.content.ContextCompat;

import org.junit.Test;
import org.junit.runner.RunWith;

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
    public void testDB(){
        ProjectsTableHelper dbHelper = new ProjectsTableHelper(context(), true);
    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


}
