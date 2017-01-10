package lv.iconcept.pastsfoto;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLDecoder;
import java.util.ArrayList;


public class FullscreenActivity extends AppCompatActivity {
    private static final int FILE_SELECT_CODE = 0;
    private String selectedImages = "";
    private String addedImages = "";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();

        setContentView(R.layout.index_activity);

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
                showFileChooser(view);
            }

            private void showFileChooser(View view) {
                if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                            Toast.makeText(getApplicationContext(), "Required to select images.", Toast.LENGTH_SHORT).show();
                    }
                    requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                }else {
                    try {
                        Intent intent = new Intent(view.getContext(), MultiImageChooserActivity.class);
                        intent.putExtra("MAX_IMAGES", 20);
                        intent.putExtra("WIDTH", 0);
                        intent.putExtra("HEIGHT", 0);
                        intent.putExtra("QUALITY", 100);
                        intent.putExtra("SELECTED_IMAGES", addedImages);

                        startActivityForResult(intent, FILE_SELECT_CODE);
                    } catch (android.content.ActivityNotFoundException ex) {
                        Log.e("ActivityExcept", ex.toString());
                        // Potentially direct the user to the Market with a Dialog
                        Toast.makeText(getApplicationContext(), "Please install a File Manager.", Toast.LENGTH_SHORT).show();
                    }
                }
            };


        };
        final LinearLayout ll = (LinearLayout) findViewById(R.id.pf_index_selectfilesButton);
        final ImageView iv = (ImageView) findViewById(R.id.pf_index_selectfilesButton_image);
        final TextView tv = (TextView) findViewById(R.id.pf_index_selectfilesButton_text);
        ll.setOnClickListener(clickListen);
        iv.setOnClickListener(clickListen);
        tv.setOnClickListener(clickListen);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getSupportActionBar().hide();
        if (resultCode == Activity.RESULT_OK && data != null) {
            ArrayList<String> fileNames = data.getStringArrayListExtra("MULTIPLEFILENAMES");
            JSONArray res = new JSONArray(fileNames);
            JSONArray bildes = new JSONArray();
            addedImages = "";
            try {
                for(int i=0;i<res.length();i+=2){
                    JSONObject temp = new JSONObject();
                    temp.put("thumb", res.getString(i+1));
                    temp.put("original", res.getString(i));
                    temp.put("count", 1);
                    if(addedImages != "") {
                        addedImages = addedImages + ";" +temp.getString("original");
                    }else {
                        addedImages = URLDecoder.decode(temp.getString("original").replace("file://", ""));
                    }
                    bildes.put(temp);
                }
                Log.v("Result --> ",""+bildes);
            } catch (JSONException e) {
                Log.e("Result Error --> ",e.toString());
            }
            selectedImages = res.toString();
            Log.d("fileUri", "success -> " + res);
        } else if (resultCode == Activity.RESULT_CANCELED && data != null) {
            String error = data.getStringExtra("ERRORMESSAGE");
            Log.d("fileUri", "response error -> " + error.toString());
            Toast.makeText(getApplicationContext(), "Bilžu izvēle atcelta.", Toast.LENGTH_SHORT).show();
        } else if (resultCode == Activity.RESULT_CANCELED) {
            JSONArray res = new JSONArray();
            Log.d("fileUri", "cancel -> " + res.toString());
            Toast.makeText(getApplicationContext(), "Bilžu izvēle atcelta.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Netika izvēlēta neviena bilde.", Toast.LENGTH_SHORT).show();
        }
    };
}
