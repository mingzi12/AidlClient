package com.mingzi.aidlclient.filehelper;

import android.content.Context;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2016/1/10.
 */
public class FileHelper {

    private Context mContext;

    public FileHelper(Context context){
        super();
        this.mContext = context;
    }

    public void saveFile(String fileName , String fileContent) throws IOException {
        FileOutputStream mFileOutputStream = mContext.openFileOutput(fileName,mContext.MODE_PRIVATE);
        mFileOutputStream.write(fileContent.getBytes());
        mFileOutputStream.close();
        Toast.makeText(mContext,"写入成功",Toast.LENGTH_SHORT);
    }

    public String readFile(String fileName) throws IOException {
        FileInputStream mFileInputStream = mContext.openFileInput(fileName);
        byte[] content = new byte[1024];
        int len=0;
        StringBuffer sb = new StringBuffer("");
        while ((len=mFileInputStream.read(content))>0){
            sb.append(new String(content,0,len));
        }
        return sb.toString();
    }

}
