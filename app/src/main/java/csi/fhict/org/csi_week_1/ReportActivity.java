package csi.fhict.org.csi_week_1;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class ReportActivity extends AppCompatActivity {

    Vibrator v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setButtons();

        Switch scaSwitch = (Switch) findViewById(R.id.scaSwitch);

        scaSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Switch scaSwitch = (Switch) findViewById(R.id.scaSwitch);
                if (scaSwitch.isChecked()) {
                    vibrate();
                } else {
                    stopVibrate();
                }
            }
        });

    }

    private void setButtons() {
        Button backButton = (Button)findViewById(R.id.buttonBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
    }
    public void goBack() {
        finish();
    }

    public void vibrate() {
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = { 500, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 500 };
        v.vibrate(pattern, 5);
    }

    public void stopVibrate() {
        v.cancel();
    }
}
