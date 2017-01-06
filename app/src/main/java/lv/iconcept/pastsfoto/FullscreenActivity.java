package lv.iconcept.pastsfoto;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Intent.EXTRA_ALLOW_MULTIPLE;


public class FullscreenActivity extends AppCompatActivity {
    private static final int FILE_SELECT_CODE = 0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        View.OnClickListener clickListen = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final LinearLayout ll = (LinearLayout) findViewById(R.id.pf_index_selectfilesButton);
                ll.setBackgroundColor(Color.parseColor("#b58220"));
                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                final LinearLayout ll = (LinearLayout) findViewById(R.id.pf_index_selectfilesButton);
                                ll.setBackgroundColor(Color.parseColor("#edaa28"));
                            }
                        },
                        300);
                showFileChooser();
                /*
                ImagePicker imagePickers = new ImagePicker();
                try {
                    imagePickers.execute((String) "getPictures", (JSONArray) new JSONArray(), new CallbackContext((String) "", (View) view));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                */
            }

            private void showFileChooser() {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);

                try {
                    startActivityForResult(Intent.createChooser(intent, "SelectFiles"), FILE_SELECT_CODE);
                } catch (android.content.ActivityNotFoundException ex) {
                    // Potentially direct the user to the Market with a Dialog
                    Toast.makeText(getApplicationContext(), "Please install a File Manager.",
                            Toast.LENGTH_SHORT).show();
                }
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
