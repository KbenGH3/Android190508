package com.example.testandroid2019;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Cards.*;
import Player.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Properties;
import java.io.FileInputStream;

//这个handler是错的，不是这个
//import java.util.logging.Handler;
import android.os.Handler;

import java.util.logging.LogRecord;
import java.util.logging.SocketHandler;

public class MainActivity extends AppCompatActivity {


    //定义一个handler 来接收子线程的信息
    Handler handler;

    TextView txtMsg;
    //txtMsg=findViewById(R.id.txtHello);

    Message msg;

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

        // 为按钮添加监听器，启动发送线程
        // 添加以后原来的onclick事件就会失效
//        Button send =findViewById(R.id.btnSend);
//        send.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View view){
//
//                String strSendMsg;
//                BufferedReader in;
//                PrintWriter out;
//
//                TextView txtInput;
//                txtInput=findViewById(R.id.btnInput);
//
//                strSendMsg=txtInput.getText().toString();
////new Mythread(strSendMsg).start();
//            }
//                                }
//
//        );

        //准备一个Handler来处理信息
                handler = new MyHandler();

                //设定好文本档用来输出信息
        txtMsg=findViewById(R.id.txtHello);

    }

    //自建一个Handler
    class MyHandler extends Handler{

        //当它收到消息时就发送到文本档
        public void handleMessage(Message msg){
            txtMsg.setText(msg.obj.toString());
        }

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

    //输入框
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
//        TextView txvLog;
//        txvLog=findViewById(R.id.txtLog);

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

        strNow=dt.toLocaleString();

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

        //显示最后一行
        txvLo2.setSelection(txvLo2.length());

    }

    //读取配置文件
    public void ReadConfig(View V){

//        Context mContext;

        String strInput;
        TextView txvInput;
        txvInput=findViewById(R.id.txtInput);

        //读取输入框 文本内容
        strInput= String.valueOf(txvInput.getText());

        TextView txvHello;
        txvHello=findViewById(R.id.txtHello);

        Properties pro=new Properties();

        //FileInputStream 必须要异常处理
        try{
            //InputStream is =  this.getClassLoader().getResourceAsStream("appConfig");//attempt to invoke virtual method
            //InputStream ip = new FileInputStream(strInput); //no such file or directory
            //InputStream pj = project.rootProject.file('local.properties').newDataInputStream() ;

//            //获取context
//            mContext = getApplicationContext();
            //只有在assets 目录下才能获取成功
            InputStream ct = getAssets().open("appConfigAssets");

            //InputStream in = new FileInputStream("/data/data/Cards");

            pro.load(ct);
            txvHello.setText(pro.getProperty("helloKey"));

        }catch (Exception e){
            txvHello.setText(e.getMessage());
        }


    }

    //存储数据
    public void saveData(View V){


        SharedPreferences sp;

        String strInput;
        TextView txvInput;
        txvInput=findViewById(R.id.txtInput);

        //读取输入框 文本内容
        strInput= String.valueOf(txvInput.getText());

        TextView txvHello;
        txvHello=findViewById(R.id.txtHello);

        Properties pro=new Properties();

        //FileInputStream 必须要异常处理
        try{

            //只有在assets 目录下才能获取成功
            InputStream ct = getAssets().open("appConfigAssets");

            pro.load(ct);

            //取得AndroidData 如果不存在，直接新建
            sp=getSharedPreferences("AndroidData",MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();

            Cards cards;
            cards = new Cards(50);

            //通过GSON保存对象
            Gson gson = new Gson();
            String json = gson.toJson(cards);


            editor.putString("helloKey",pro.getProperty("helloKey"));
            editor.putString("cards",json);
            editor.commit();

            Cards cardSaved;
            String GetJson;
            Type type = new TypeToken<Cards>() {}.getType();

            //从sharePreferences中恢复对象
            GetJson =sp.getString("cards","failCard");
            cardSaved = gson.fromJson(GetJson,type);

            //输出之前保存的对象
            txvHello.setText("Saved cards : " + String.valueOf(cardSaved.count));


        }catch (Exception e){
            txvHello.setText(e.getMessage());
        }
    }

    //清除输出
    public void Clear(View V){

        String StrLog;
        TextView txvHello;
        txvHello=findViewById(R.id.txtHello);
        txvHello.setText("");

        TextView txvTime;
        txvTime=findViewById(R.id.txtTime);
        txvTime.setText("");

        EditText txvLo2;
        txvLo2=findViewById(R.id.txtLOG2);
        txvLo2.setText("");

    }

    //通过Socket 发送信息 (客户端）
    //android 4.0以后必须通过 非主线程来建socket
    public void SocketSend(View V) {

        String strSendMsg;
        BufferedReader in;
        PrintWriter out;

        TextView txvHello;
        txvHello = findViewById(R.id.txtHello);

        EditText txvLo2;
        txvLo2 = findViewById(R.id.txtLOG2);

        txvLo2.setText("start sending");
//
//        try {
//            txvLo2.setText("before socket");
//            Socket socket = new Socket("127.0.0.1", 9999);
//            strSendMsg = txvHello.getText().toString();
//
//            txvLo2.setText(strSendMsg);
//
//            in = new BufferedReader(new InputStreamReader((socket.getInputStream())));
//            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
//            out.println("hello socket!");
//            out.flush();
//
//            in.close();
//            out.close();
//            socket.close();
//
//            txvLo2.setText("sent socket!");
//
//        } catch (Exception e) {
//            txvHello.setText(e.getMessage());
//            txvHello.setText("Exception");
//        }

        //建一个服务器线程
        new ServerThread("ServerStart").start();

        txvLo2.setText(txvLo2.getText()+ "\n" + "Server Start");
        txvLo2.setSelection(txvLo2.length());

        //建一个客户端线程
        new MyThread("SocketStart").start();

        txvLo2.setText(txvLo2.getText()+ "\n" + "Client Start");
        txvLo2.setSelection(txvLo2.length());

    }

        //添加一个线程去发送socket信息 ? 但是socket 就一定要有线程才能发送吗？---> android 4.0 之后一定要子线程才能建socket
        class MyThread extends Thread{
            public String strTxt;

            public MyThread(String strInTxt){
                strTxt=strInTxt;
            }

            //每个线程都有一个run函数
            //将本来准备在 send 按钮上做的逻辑全部搬到这个run函数里面
            public void run(){

                String strSendMsg;
                String TargetIp;

                BufferedReader in;
                PrintWriter out;

                TextView txtHello;
                txtHello = findViewById(R.id.txtHello);

                TextView txvInput;
                txvInput = findViewById(R.id.txtInput);

                EditText txvLo2;
                txvLo2 = findViewById(R.id.txtLOG2);

                //可能不是在主线程里，所以不能对UI上的控件进行操作
               // txvLo2.setText("start sending");

                //但是可以读取UI上的内容
                TargetIp=txtHello.getText().toString();
                strSendMsg=txvInput.getText().toString();

                try {
                    //txvLo2.setText("before socket");
                    Socket socket = new Socket(TargetIp, 9999);
                    //txvLo2.setText("after socket");

                    in = new BufferedReader(new InputStreamReader((socket.getInputStream())));
                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                    out.println(strSendMsg);
                    out.flush();

                    in.close();
                    out.close();
                    socket.close();

                    //txvLo2.setText("sent socket!");

                } catch (Exception e) {
                    //txvHello.setText(e.getMessage());
                    //txvHello.setText("Exception");
                }
            }
        }


    //添加一个服务器线程 ，用来模拟一个服务器的主线程
    class ServerThread extends Thread{
        public String strTxt;

        public ServerThread(String strInTxt){
            strTxt=strInTxt;
        }

        //每个线程都有一个run函数
        public void run(){

            try {
                ServerSocket service = new ServerSocket(9999);
                while (true){
                    //不断循环直到 accept 到客户端的连接
                    Socket socket = service.accept();
                    new Thread(new ResponseThread(socket)).start();
                }

            } catch (Exception e) {
                //txvHello.setText(e.getMessage());
                //txvHello.setText("Exception");
            }
        }
    }

    //添加一个服务器回答线程
    class ResponseThread extends Thread{
        Socket socket = null;

        public ResponseThread(Socket socket){
            this.socket=socket;
        }

        //每个线程都有一个run函数
        public void run(){

            InputStream input;
            BufferedReader bff;
            String line = null;

            try {
                input = socket.getInputStream();
                bff = new BufferedReader(new InputStreamReader(input));

                //关关闭socket 这里没有output，其实应该在output完以后才shutdown
                socket.shutdownOutput();

                // 读取客户端输入信息
                while ((line = bff.readLine()) != null) {
                    //System.out.print(line);

                    Bundle bundle=new Bundle();
                    bundle.putString("msg",line);

                    msg=handler.obtainMessage();
                    msg.setData(bundle);
                    //其实不用bundle也可以直接发送String
                    msg.obj=line;
                    handler.sendMessage(msg);
                }

                //关闭输入输出流
                bff.close();
                input.close();
                socket.close();

            } catch (Exception e) {
                //txvHello.setText(e.getMessage());
                //txvHello.setText("Exception");
            }
        }
    }



    }


