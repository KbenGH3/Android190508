package com.example.testandroid2019;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.method.MovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import Cards.*;
import Player.*;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
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

    //输出日志
    public void addLog(View V){

        String StrLog;
        TextView txvLog;
        txvLog=findViewById(R.id.txtLog);

        EditText txvLo2;
        txvLo2=findViewById(R.id.txtLOG2);

        //new date();
        StrLog="log";

//        txvLog.setText(txvLog.getText()+ "\r\n" + StrLog);
        txvLo2.setText(txvLo2.getText()+ "\n" + StrLog);
//        txvLo2.setMovementMethod(ScrollingMovementMethod.getInstance());
//        txvLo2.moveCursorToVisibleOffset();
          txvLo2.setSelection(txvLo2.length());
    }
    public void Test(){

       System.out.println("just a test");

    }


    //骰子
    public void Dice(View V){
        TextView txv;
        int intDiceRst;

        intDiceRst=((int) (Math.random() * 10 ) % 6) + 1;

        txv=findViewById(R.id.txtHello);
        txv.setText(String.valueOf(intDiceRst));
    }

    //显示时间
    public void ShowNow(View V){
        TextView txvTime;
        String strNow;

        Date dt=new Date();

        strNow=dt.toGMTString();
//        strNow=dt.toLocaleString();

        txvTime=findViewById(R.id.txtTime);
        txvTime.setText(strNow);
    }

    //生成牌
    public  void CreateCards(View V){

        TextView txv;
        txv=findViewById(R.id.txtHello);

        Cards cards;
        cards = new Cards(10);

        txv.setText(cards.msg + " : " + cards.count + "  id of card 1 :" + cards.allCard[0].card_id);

    }

    //洗牌
    public  void  CardShuffle(View V){

        int i;
        TextView txv;
        txv=findViewById(R.id.txtHello);

        Cards cards;
        cards = new Cards(10);
        cards.shuffle();

        for(i=0;i<cards.count;i++){
            txv.setText( txv.getText() + "," + String.valueOf(cards.allCard[i].card_id));
        }
    }

    //派牌
    public  void  CardDeal(View V){

        int i;
        int j;

        //通过日志框输出
        TextView txv;
        txv=findViewById(R.id.txtHello);

        Cards cards;
        cards = new Cards(54);
        cards.shuffle();

        Players players;
        players = new Players( 4);

        j=0;
        for(i=0;i<cards.count;i++){

            if(j==players.count){ j=0;}
            cards.allCard[i].owner=String.valueOf(players.allPlayer[j].player_id);
            j++;
        }


        //输出
        EditText txvLo2;
        txvLo2=findViewById(R.id.txtLOG2);

        String StrLog;
        StrLog="Player1: ";
        txvLo2.setText(txvLo2.getText()+ "\n" + StrLog);
        txvLo2.setSelection(txvLo2.length());


        for(i=0;i<cards.count;i++){

            //只输出 player【1】
            if (cards.allCard[i].owner==String.valueOf(players.allPlayer[1].player_id)){
                txvLo2.setText(txvLo2.getText()+ " ," +  String.valueOf(cards.allCard[i].card_id));
            }

        }


    }

}
