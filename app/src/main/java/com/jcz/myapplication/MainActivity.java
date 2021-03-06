package com.jcz.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jcz.myapplication.downloadUtils.DownLoadService;
import com.jcz.myapplication.downloadUtils.DownloadFileUtils;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public static String downUrl = "http://dl.coolapkmarket.com/down/apk_file/2016/0808/com.coolapk.market-6.10.4-1608081.apk?_upt=298cd5b01470995233";
    public static String filename = "coolPacket";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testDownload();
    }

    private void testDownload() {
        Button download = (Button) findViewById(R.id.download);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloadFileUtils.getInstand().initDownloadFileUtils(MainActivity.this, true, null);
                DownloadFileUtils.getInstand().DownloadFileOnNewThread(downUrl,filename);
            }
        });

        Button downloadInBackground = (Button) findViewById(R.id.downloadInBackground);
        downloadInBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, DownLoadService.class));//开启服务启动后台下载处理。问题：app退出后，服务没有退出，若是绑定，切换Activity时，服务不能保证继续运行。
            }
        });
    }


}
