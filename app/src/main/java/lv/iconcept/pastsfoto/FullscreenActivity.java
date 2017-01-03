package lv.iconcept.pastsfoto;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import static lv.iconcept.pastsfoto.R.mipmap.pf_logo;

public class FullscreenActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        View.OnClickListener clickListen = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final LinearLayout ll = (LinearLayout) findViewById(R.id.pf_index_selectfilesButton);
                ll.setBackgroundColor(Color.parseColor("#b58220"));
                Timer t = new Timer();
                t.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        final LinearLayout ll = (LinearLayout) findViewById(R.id.pf_index_selectfilesButton);
                        ll.setBackgroundColor(Color.parseColor("#edaa28"));
                    }
                }, 2000);
                AlertDialog alertDialog = new AlertDialog.Builder(FullscreenActivity.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("hue hue hue");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                alertDialog.show();
            }

        };
        final LinearLayout ll = (LinearLayout) findViewById(R.id.pf_index_selectfilesButton);
        final ImageView iv = (ImageView) findViewById(R.id.pf_index_selectfilesButton_image);
        final TextView tv = (TextView) findViewById(R.id.pf_index_selectfilesButton_text);
        ll.setOnClickListener(clickListen);
        iv.setOnClickListener(clickListen);
        tv.setOnClickListener(clickListen);
    }
}
