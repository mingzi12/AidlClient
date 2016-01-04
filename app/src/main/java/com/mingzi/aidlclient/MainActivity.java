package com.mingzi.aidlclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mingzi.aidl.IPerson;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private IPerson mPerson;
    private TextView mTextView;
    private Button mButton;
    private EditText mEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent mAidlIntent = new Intent();
        mAidlIntent.setAction("com.mingzi.MyAidlService");
        bindService(mAidlIntent, mConn, BIND_AUTO_CREATE);
        initView();
    }
    public void initView(){
        mEditText = (EditText) findViewById(R.id.edittxt);
        mButton = (Button) findViewById(R.id.mAidlBtn);
        mTextView = (TextView) findViewById(R.id.nametxt);
        mButton.setOnClickListener(this);
    }
    private ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("onServiceConnected is be called");
            mPerson = IPerson.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onClick(View v) {
        String num = mEditText.getText().toString();
        int nameNum = Integer.valueOf(num);
        if (nameNum>=0&&nameNum<=4){
            try{
                mTextView.setText(mPerson.queryPerson(nameNum).toString());
            }
            catch (RemoteException e){
                e.printStackTrace();
            }
        }
    }
}
