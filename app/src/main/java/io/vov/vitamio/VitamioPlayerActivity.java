package io.vov.vitamio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.uzmap.pkg.uzcore.UZResourcesIDFinder;

import org.json.JSONException;
import org.json.JSONObject;

import io.vov.vitamio.widget.VideoView;

public class VitamioPlayerActivity extends Activity {

    private String streamkey;
    private String roomID;
    private String userID;
    private String msg_path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        super.onCreate(savedInstanceState);
        Vitamio.isInitialized(getApplicationContext());
        int layoutId = UZResourcesIDFinder.getResLayoutID("mo_vitamio_activity_main");
        if (layoutId > 0) {
            setContentView(layoutId);
        } else {
            Toast.makeText(this, "文件丢失!", Toast.LENGTH_LONG).show();
            return;
        }
        vitamioplay();
    }

    private void vitamioplay() {
        // --- 获取连接房间参数
        Intent intent = getIntent();
        String msg = intent.getStringExtra("appParam");
        try {
            JSONObject object = new JSONObject(msg);
            streamkey = object.getString("streamkey");
            roomID = object.getString("roomID");
            userID = object.getString("userID");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String path = "http://o9ve1mre2.bkt.clouddn.com/raw_天宫二号";
        msg_path = "rtmp://play.jiashizhan.com/live/streamkey=" + streamkey + "&roomid=" + roomID + "&dataid=" + userID + "&isApp=1";
        Log.e("msg_path", msg_path);
        VideoView mVideoView;
        int surface_viewId = UZResourcesIDFinder.getResIdID("surface_view");
        mVideoView = (VideoView) findViewById(surface_viewId);
        mVideoView.setVideoPath(msg_path);
//        mVideoView.setMediaController(new MediaController(this));
        mVideoView.requestFocus();

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                // optional need Vitamio 4.0
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });

    }
}
