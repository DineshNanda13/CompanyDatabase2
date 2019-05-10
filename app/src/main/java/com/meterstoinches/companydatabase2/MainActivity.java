package com.meterstoinches.companydatabase2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView t1;
    DatabaseHandler mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=(TextView) findViewById(R.id.txt);
        t1.setText("");
        t1.setMovementMethod(new ScrollingMovementMethod());
        mydb= new DatabaseHandler(this);
        mydb.getReadableDatabase();
        displayAll();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.adding){
            addOneCompany();
            update();
            return true;
        }
        if(id==R.id.addingMany){
            addMany();
            update();
            return true;
        }
        if(id==R.id.deleteLastRow){
            mydb.deleteLastRow();
            update();
            return true;
        }
        if(id==R.id.deletingall){
            mydb.deleteAll();
            update();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void addOneCompany(){
        mydb.addCompany("AnkushPreet","xyz","Lsalle","HX5","a1","ankush@bitcoin",
                "ANKU","Dinesh","Softwaretester");
    }
    private void addMany(){
        mydb.addCompany("rajvirKaur","abc","avenue","2h6","a2","rajvir@cegep","rajjo","Akshil","khanAcademy");
        mydb.addCompany("rajvirKaur","abc","avenue","2h6","a2","rajvir@cegep","rajjo","Akshil","khanAcademy");
        mydb.addCompany("rajvirKaur","abc","avenue","2h6","a2","rajvir@cegep","rajjo","Akshil","khanAcademy");
        mydb.addCompany("rajvirKaur","abc","avenue","2h6","a2","rajvir@cegep","rajjo","Akshil","khanAcademy");
        mydb.addCompany("rajvirKaur","abc","avenue","2h6","a2","rajvir@cegep","rajjo","Akshil","khanAcademy");
        mydb.addCompany("rajvirKaur","abc","avenue","2h6","a2","rajvir@cegep","rajjo","Akshil","khanAcademy");
        mydb.addCompany("rajvirKaur","abc","avenue","2h6","a2","rajvir@cegep","rajjo","Akshil","khanAcademy");
        mydb.addCompany("rajvirKaur","abc","avenue","2h6","a2","rajvir@cegep","rajjo","Akshil","khanAcademy");
        mydb.addCompany("rajvirKaur","abc","avenue","2h6","a2","rajvir@cegep","rajjo","Akshil","khanAcademy");
        mydb.addCompany("rajvirKaur","abc","avenue","2h6","a2","rajvir@cegep","rajjo","Akshil","khanAcademy");
    }
    private void displayAll(){
        List<Company> companies= mydb.getallCompany();
        if (companies.size()==0){
            t1.setText("No records in the Database");
        }

        for (Company cn : companies) {
            String log = "CompanyRef: "+cn.getComapnyRef()+" , FormalName: " + cn.getFormalName()
                    + " , CompanyTypeCode: " + cn.getCompanyTypeCode()+" , MainAddress: "+cn.getMainAdress()
                    +" , MainPostCode: "+ cn.getMainPostcode()+" , ReceptionNo: "+ cn.getReceptionNo()
                    +" , WebsiteURL: "+cn.getWebsiteURL()+" , Customer: "+cn.getCustomer()+" , Supplier: "
                    +cn.getSupplier()+" , CompanyNotes: "+cn.getCompaynotes();
            t1.append(log+ "\n");
        }
    }
    private void update(){
        t1.setText("");
        displayAll();
    }
}
