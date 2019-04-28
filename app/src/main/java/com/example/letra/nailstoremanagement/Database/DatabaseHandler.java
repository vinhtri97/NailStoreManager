package com.example.letra.nailstoremanagement.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DatabaseHandler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "NailStoreDatabase";

    // Table Names
    private static final String TABLE_Employee = "Employee";
    private static final String TABLE_CustomerAppointment = "Customer_Appoinment";
    private static final String TABLE_NailSkillList = "Nail_Skill_List";

    // Common column names
    private static final String ID = "id";
    private Context context;
    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        this.getWritableDatabase();
    }

    private static final String[] EMPLOYEE_TABLE_INSERT_INITIALIZE_SQLSTATEMENTS ={
            "INSERT INTO EMPLOYEE (FIRST_NAME,LAST_NAME,PHONE_NUMBER,PASSWORD,SSN,AVATAR_SRC,SKILLS_JSON,SCHEDULE_JSON) VALUES (\"John\",\"Doe\",\"361855789\",\"jdoe\",\"771-07-1426\",\"jdoe\",\"\",\"\")",
            "INSERT INTO EMPLOYEE (FIRST_NAME,LAST_NAME,PHONE_NUMBER,PASSWORD,SSN,AVATAR_SRC,SKILLS_JSON,SCHEDULE_JSON) VALUES (\"Jessica\",\"Wilson\",\"3617169893\",\"jwilson123\",\"185-85-1886\",\"jwilson\",\"\",\"\");",
            "INSERT INTO EMPLOYEE (FIRST_NAME,LAST_NAME,PHONE_NUMBER,PASSWORD,SSN,AVATAR_SRC,SKILLS_JSON,SCHEDULE_JSON) VALUES (\"Emily\",\"Chloe\",\"3616583960\",\"chloerocks\",\"255-44-1694\",\"echloe\",\"\",\"\");",
            "INSERT INTO EMPLOYEE (FIRST_NAME,LAST_NAME,PHONE_NUMBER,PASSWORD,SSN,AVATAR_SRC,SKILLS_JSON,SCHEDULE_JSON) VALUES (\"Megan\",\"Lopez\",\"3618480903\",\"meganz92\",\"881-47-8844\",\"mlopez\",\"\",\"\");",
            "INSERT INTO EMPLOYEE (FIRST_NAME,LAST_NAME,PHONE_NUMBER,PASSWORD,SSN,AVATAR_SRC,SKILLS_JSON,SCHEDULE_JSON) VALUES (\"Hannah\",\"Gia\",\"3615683708\",\"iamhann\",\"851-06-8741\",\"hgia\",\"\",\"\");",
            "INSERT INTO EMPLOYEE (FIRST_NAME,LAST_NAME,PHONE_NUMBER,PASSWORD,SSN,AVATAR_SRC,SKILLS_JSON,SCHEDULE_JSON) VALUES (\"Lucy\",\"Liu\",\"3618081724\",\"liulucy7\",\"821-88-9961\",\"lliu\",\"\",\"\");",
            "INSERT INTO EMPLOYEE (FIRST_NAME,LAST_NAME,PHONE_NUMBER,PASSWORD,SSN,AVATAR_SRC,SKILLS_JSON,SCHEDULE_JSON) VALUES (\"Davida\",\"Marseloso\",\"3616958061\",\"dmarselus\",\"125-48-9664\",\"dmarselus\",\"\",\"\");"};


    private static final String[] SKILL_LIST_TABLE_INSERT_INITIALIZE_SQLSTATEMENTS ={
            "INSERT INTO NAIL_SKILLS (NAIL_SKILL_NAME, NAIL_SKILL_PRICE, NAIL_SKILL_DESCRIPTION,NAIL_SKILL_IMG_SRC) VALUES (\"Acrylic Dip\",\"9.99\",\"is a process where an adhesive is applied to nails or nail tips. While the nails are wet they are dipped into a polymer powder, a total of 2-3 times. The nails are then filed into shape and shined.\",\"skill1\")",
            "INSERT INTO NAIL_SKILLS (NAIL_SKILL_NAME, NAIL_SKILL_PRICE, NAIL_SKILL_DESCRIPTION,NAIL_SKILL_IMG_SRC) VALUES (\"Acrylic Toenails\",\"10.99\",\"After nails and cuticles are cleaned, a small amount of liquid acrylic is applied to the nail. Once this acrylic hardens, it can be sculptured, painted and buffed.\",\"skill2\")",
            "INSERT INTO NAIL_SKILLS (NAIL_SKILL_NAME, NAIL_SKILL_PRICE, NAIL_SKILL_DESCRIPTION,NAIL_SKILL_IMG_SRC) VALUES (\"Acrylics Sculpt\",\"19.99\",\"Utilize a temporary form that fits underneath the natural nail. Acrylic is applied over the form and then shaped to the desired length.\",\"skill3\")",
            "INSERT INTO NAIL_SKILLS (NAIL_SKILL_NAME, NAIL_SKILL_PRICE, NAIL_SKILL_DESCRIPTION,NAIL_SKILL_IMG_SRC) VALUES (\"Brush-on Gel Polish\",\"14.99\",\"This is a type of gel polish that is brushed on the nails. Because of its thicker consistency, it generally holds its shape without running. It also helps to strengthen natural nails.\",\"skill4\")",
            "INSERT INTO NAIL_SKILLS (NAIL_SKILL_NAME, NAIL_SKILL_PRICE, NAIL_SKILL_DESCRIPTION,NAIL_SKILL_IMG_SRC) VALUES (\"Colored Acrylics\",\"9.99\",\"Color acrylics are typically available in powder form. Use them with any liquid monomer. They are long-lasting and hold up well with minimal chipping.\",\"skill5\")",
            "INSERT INTO NAIL_SKILLS (NAIL_SKILL_NAME, NAIL_SKILL_PRICE, NAIL_SKILL_DESCRIPTION,NAIL_SKILL_IMG_SRC) VALUES (\"Foot Massage\",\"12.99\",\"A foot massage is applying pressure to the feet, in effort to create a relaxing effect on the body. Two popular type of foot massage are Chinese and Reflexology.\",\"skill6\")",
            "INSERT INTO NAIL_SKILLS (NAIL_SKILL_NAME, NAIL_SKILL_PRICE, NAIL_SKILL_DESCRIPTION,NAIL_SKILL_IMG_SRC) VALUES (\"Full-Coverage Nail Art and Nail Tech Skills\",\"24.99\",\"Full-coverage nail art covers the entire area, attaching easily to the natural nail. The art is available in a wide variety of colors and designs.\",\"skill7\")"
    };
    private static final String CREATE_TABLE_EMPLOYEE = "CREATE TABLE EMPLOYEE " +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "FIRST_NAME TEXT NOT NULL, " +
            "LAST_NAME TEXT NOT NULL, " +
            "PHONE_NUMBER TEXT NOT NULL, " +
            "PASSWORD TEXT NOT NULL, " +
            "SSN TEXT NOT NULL, " +
            "AVATAR_SRC TEXT NOT NULL, " +
            "SKILLS_JSON TEXT NOT NULL, " +
            "SCHEDULE_JSON TEXT NOT NULL)";

    private static final String CREATE_TABLE_CUSTOMER_APPOINMENT = "CREATE TABLE CUSTOMER_APPOINTMENT (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "FULL_NAME TEXT NOT NULL, " +
            "PHONE_NUMBER TEXT NOT NULL, " +
            "PREFERRED_EMPLOYEE TEXT NOT NULL, " +
            "APPOINTMENT_TIME TEXT NOT NULL, " +
            "APPOINTMENT_DATE TEXT NOT NULL);";

    private static final String CREATE_TABLE_NAIL_SKILL_LIST = "CREATE TABLE NAIL_SKILLS (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "NAIL_SKILL_NAME TEXT NOT NULL, " +
            "NAIL_SKILL_PRICE TEXT NOT NULL, " +
            "NAIL_SKILL_DESCRIPTION TEXT NOT NULL, " +
            "NAIL_SKILL_IMG_SRC TEXT NOT NULL)";
    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_EMPLOYEE);
        db.execSQL(CREATE_TABLE_CUSTOMER_APPOINMENT);
        db.execSQL(CREATE_TABLE_NAIL_SKILL_LIST);

        for (String sqlStatements:EMPLOYEE_TABLE_INSERT_INITIALIZE_SQLSTATEMENTS) {
            db.execSQL(sqlStatements);
        }

        for (String sqlStatements:SKILL_LIST_TABLE_INSERT_INITIALIZE_SQLSTATEMENTS) {
            db.execSQL(sqlStatements);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Employee);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CustomerAppointment);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NailSkillList);
        // create new tables
        onCreate(db);
    }
//aa
    public List<Nail_Skill> getNailSkills(){
        List<Nail_Skill> nail_skills = new ArrayList<Nail_Skill>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM NAIL_SKILLS",null);
        if (cursor.moveToFirst()) {
            int index = 0;
            do {
                //Log.d("ColumnIndex1Content", cursor.getString(1));
                nail_skills.add(new Nail_Skill(index, cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4)));
                index++;
            } while (cursor.moveToNext());
        }
        db.close();
        return nail_skills;
    }

    public List<Employee> getEmployees(){
        List<Employee> employees = new ArrayList<Employee>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM EMPLOYEE",null);
        if (cursor.moveToFirst()) {
            do {
                employees.add(new Employee(cursor.getString(1), cursor.getString(3),
                        cursor.getString(6)));
            } while (cursor.moveToNext());
        }
        db.close();
        return employees;
    }
}