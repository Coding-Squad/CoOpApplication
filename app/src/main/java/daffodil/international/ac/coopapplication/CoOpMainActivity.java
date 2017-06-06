package daffodil.international.ac.coopapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import stanford.androidlib.SimpleActivity;

import static java.sql.Types.INTEGER;

public class CoOpMainActivity extends SimpleActivity {
    private static final String TAG = "CoOpMainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_co_op_main);
    }

    public void goToSignUpAsPage(View view) {
        Intent signUpPageIntent = new Intent
                (this, SignUpAsActivity.class);
        startActivity(signUpPageIntent);
    }

    private void doQuery(){
        SQLiteDatabase co_op_sqLiteDatabase = SQLiteDatabase.openDatabase("coop_database", null, MODE_PRIVATE);
        co_op_sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS users" +
                "(userId LONG PRIMARY KEY NOT NULL,Password VARCHAR,email VARCHAR,acountStatus BOOLEAN,secretQuestion VARCHAR,userRole INTEGER);");
        co_op_sqLiteDatabase.execSQL("INSERT INTO users VAlUES(1002l, '123456', 'mazharul@diit.info', true, 'Masum', 1)");

    }
}
