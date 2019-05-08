package com.example.testandroid2019;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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

    public void Hello_onlick(View V){

        TextView txv;
        txv=findViewById(R.id.txtHello);
        txv.setText("HelloClikck");

    }

    public void println_hello(View V){

        System.out.println("hello");

    }

    public void Ipnut(View V){

        String strInput;
        TextView txvInput;
        txvInput=findViewById(R.id.txtInput);

        strInput= String.valueOf(txvInput.getText());

        TextView txvHello;
        txvHello=findViewById(R.id.txtHello);
        txvHello.setText(strInput);

    }

    public void addLog(View V){

        String StrLog;
        TextView txvLog;
        txvLog=findViewById(R.id.txtLog);

        //new date();
        StrLog="log";

        txvLog.setText(txvLog.getText()+ "\r\n" + StrLog);

    }
    public void Test(){

       System.out.println("just a test");

    }


    public void Dice(View V){
        TextView txv;
        int intDiceRst;

        intDiceRst=((int) (Math.random() * 10 ) % 6) + 1;

        txv=findViewById(R.id.txtHello);
        txv.setText(String.valueOf(intDiceRst));
    }
}
