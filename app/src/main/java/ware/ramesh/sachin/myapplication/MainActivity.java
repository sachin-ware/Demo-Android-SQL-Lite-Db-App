package ware.ramesh.sachin.myapplication;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity
{

    DataBaseHelper database;

    EditText name,mobile,email,password,address,confirm_password;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database=new DataBaseHelper(this);

        name=(EditText)findViewById(R.id.etxt_name);
        mobile=(EditText)findViewById(R.id.etxt_mobile);
        email=(EditText)findViewById(R.id.etxt_email);
        password=(EditText)findViewById(R.id.etxt_pwd);
        confirm_password=(EditText)findViewById(R.id.etxt_cpwd);
        address=(EditText)findViewById(R.id.etxt_address);

        Log.d("===========>", " Activity created");

    }

    public void saveRegister(View v)
    {
        try
        {
            Toast.makeText(this,""+name.getText().toString().length(),Toast.LENGTH_SHORT).show();
            if(name.getText().toString().length()==0)
            {
                name.setError("Insert Value");
                return;
            }

            if(!isValidMobile(mobile.getText().toString()))
            {
                mobile.setError("Invalid Mobile");
                return;
            }


            if(!isValidEmail(email.getText().toString()))
            {
                email.setError("invalid email ");
                return;
            }





            boolean saved=database.insertData(name.getText().toString(),mobile.getText().toString());

            if(saved)
                Toast.makeText(this,"RECORD INSERTED",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this,"FAILED TO INSERT",Toast.LENGTH_SHORT).show();
            Log.d("===========>", " saved");

        }
        catch(Exception e)
        {
            Log.d("Exception====>","saveRegister message:"+e.getMessage());
        }
    }

    public void clearRegister(View v)
    {
        ((EditText)findViewById(R.id.etxt_name)).setText("");
        ((EditText)findViewById(R.id.etxt_address)).setText("");
        ((EditText)findViewById(R.id.etxt_email)).setText("");
        ((EditText)findViewById(R.id.etxt_mobile)).setText("");
        ((EditText)findViewById(R.id.etxt_pwd)).setText("");
        ((EditText)findViewById(R.id.etxt_cpwd)).setText("");
        Log.d("===========>", " clearRegister");
    }


    public void viewAllData(View v)
    {
        try
        {
            Cursor cursor=database.getAllData();
            if(cursor.getCount()==0)
            {
                Toast.makeText(this,"Dont have any data in db..",Toast.LENGTH_SHORT).show();
                return;
            }

            StringBuffer buffer=new StringBuffer();
            while (cursor.moveToNext())
            {
                buffer.append("Id:"+cursor.getString(0)+"\n");
                buffer.append("Name:"+cursor.getString(1)+"\n");
                buffer.append("Mobile:"+cursor.getString(2)+"\n\n");
            }

            showBuilder("Users",buffer.toString());

        }
        catch(Exception e)
        {
            Log.d("Exception====>","viewAllData message:"+e.getMessage());
        }
    }

    public void deleteAllRecords(View v)
    {
        try
        {
            int rows= database.deleteRecords();
            Toast.makeText(this,rows+" rows deleted . . .",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Log.d("Exception====>","deleteAll message:"+e.getMessage());
        }

    }
    public  void showBuilder(String title,String message)
    {
        try
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle(title);
            builder.setCancelable(true);
            builder.setMessage(message);
            builder.show();

        }
        catch (Exception e)
        {
            Log.d("Exception=====>","showBuilder Message: "+e.getMessage());
        }
    }


    // validating email id
    private boolean isValidEmail(String email)
    {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass)
    {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }



    private boolean isValidMobile(String phone)
    {
        return android.util.Patterns.PHONE.matcher(phone).matches();

    }

}


      /*  try
        {


        }
        catch (Exception e)
        {
        Log.d("Exception=====>","clearRecords Message:"+e.getMessage());
        }*/
