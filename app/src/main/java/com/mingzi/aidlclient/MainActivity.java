package com.mingzi.aidlclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingzi.aidl.IPerson;
import com.mingzi.aidlclient.database.MyDBOpenHelper;
import com.mingzi.aidlclient.database.MySQLiteDatabase;
import com.mingzi.aidlclient.filehelper.FileHelper;
import com.mingzi.aidlclient.fragment.MyFragment;
import com.mingzi.aidlclient.testjson.Author;
import com.mingzi.aidlclient.testjson.Book;
import com.mingzi.aidlclient.testjson.JsonActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private IPerson mPerson;
    private TextView mTextView;
    private Button mButton;
    private EditText mEditText;
    private EditText mFileName;
    private EditText mFileContent;
    private Button saveBtn;
    private Button readBtn;
    private Button clearBtn;
    private Button insertBtn;
    private Button queryBtn;
    MyDBOpenHelper myDBOpenHelper;
    MySQLiteDatabase mySQLiteDatabase;
    private Button mJsonBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myDBOpenHelper = new MyDBOpenHelper(MainActivity.this,null,null,1);
        initFileView();
        initDatabaseBtn();
        Intent mAidlIntent = new Intent();
        mAidlIntent.setAction("com.mingzi.MyAidlService");
        bindService(mAidlIntent, mConn, BIND_AUTO_CREATE);
        initView();
    }
    public void initView(){
        mJsonBtn = (Button) findViewById(R.id.JsonBtn);
        mEditText = (EditText) findViewById(R.id.edittxt);
        mButton = (Button) findViewById(R.id.mAidlBtn);
        mTextView = (TextView) findViewById(R.id.nametxt);
        mButton.setOnClickListener(this);
        mJsonBtn.setOnClickListener(this);
    }
    private ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("onServiceConnected is be called");
            mPerson = IPerson.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println("onServiceDiconnected");
        }
    };
    private void initDatabaseBtn(){
        insertBtn = (Button) findViewById(R.id.insertbtn);
        queryBtn = (Button) findViewById(R.id.querybtn);
        Button updatebtn = (Button) findViewById(R.id.updatebtn);
        Button deletebtn = (Button) findViewById(R.id.deletebtn);
        updatebtn.setOnClickListener(this);
        deletebtn.setOnClickListener(this);
        insertBtn.setOnClickListener(this);
        queryBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (mySQLiteDatabase==null)
            mySQLiteDatabase = new MySQLiteDatabase(MainActivity.this,myDBOpenHelper);
        switch (v.getId()){
            case R.id.mAidlBtn:
            String num = mEditText.getText().toString();
            int nameNum = Integer.valueOf(num);
            if (nameNum>=0 && nameNum<=4){
                try{
                    mTextView.setText(mPerson.queryPerson(nameNum).toString());
                }
                catch (RemoteException e){
                    e.printStackTrace();
                }
            }
                mEditText.setText("");
                break;
            case R.id.savebtn:
                FileHelper mSaveFileHelper = new FileHelper(getApplicationContext());
                String fileName = mFileName.getText().toString();
                String fileContent = mFileContent.getText().toString();
                try{
                    mSaveFileHelper.saveFile(fileName,fileContent);
                } catch (IOException e){
                    e.printStackTrace();
                }
                break;
            case R.id.readbtn:
                FileHelper mReadFileHelper = new FileHelper(getApplicationContext());
                String fileReadName = mFileName.getText().toString();
                try{
                    String content = mReadFileHelper.readFile(fileReadName);
                    mFileContent.setText(content);
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;
            case R.id.clearbtn:
                mFileContent.setText("");
                mFileName.setText("");
                break;
            case R.id.insertbtn:
                mySQLiteDatabase.insert();
                break;
            case R.id.querybtn:
                mySQLiteDatabase.query();
                break;
            case R.id.updatebtn:
                mySQLiteDatabase.update();
                break;
            case R.id.deletebtn:
                mySQLiteDatabase.delete();
                break;
            case R.id.JsonBtn :
                Author mAuthor = new Author(007,"Bruce Eckel");
                Book mBook = new Book("Think in java",mAuthor);
                Intent mJsonIntent = new Intent(MainActivity.this, JsonActivity.class);
                mJsonIntent.putExtra("mJson",new Gson().toJson(mBook));
                startActivity(mJsonIntent);
                break;
            default:

                break;
        }

    }
    public void initFileView(){
        mFileName = (EditText) findViewById(R.id.filenametxt);
        mFileContent = (EditText) findViewById(R.id.filecontent);
        saveBtn = (Button) findViewById(R.id.savebtn);
        readBtn = (Button) findViewById(R.id.readbtn);
        clearBtn = (Button) findViewById(R.id.clearbtn);
        saveBtn.setOnClickListener(this);
        readBtn.setOnClickListener(this);
        clearBtn.setOnClickListener(this);
    }
}
