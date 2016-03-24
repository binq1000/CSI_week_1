package csi.fhict.org.csi_week_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Criminal receivedCriminal;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setData();

        Button b = (Button)findViewById(R.id.button1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToReportScreen();
            }
        });

        CrimeListAdapter adapter = new CrimeListAdapter(MainActivity.this, receivedCriminal.crimes);
        list = (ListView) findViewById(R.id.crimeList);
        list.setAdapter(adapter);
    }

    public void goToReportScreen() {
        Intent intent = new Intent(this, ReportActivity.class);
        startActivity(intent);
    }

    public void setData() {
        Intent intent = getIntent();

        receivedCriminal = (Criminal) intent.getSerializableExtra("criminal");

        TextView nameText = (TextView) findViewById(R.id.name);
        TextView genderText = (TextView) findViewById(R.id.gender);
        TextView descriptionText = (TextView) findViewById(R.id.description);
        TextView ageText = (TextView) findViewById(R.id.age);
        TextView bountyText = (TextView) findViewById(R.id.bounty);
        ImageView image = (ImageView) findViewById(R.id.image);

//        image.setImageDrawable(receivedCriminal.mugshot);
        try {
            image.setImageResource(receivedCriminal.imageID);
        } catch (OutOfMemoryError oome) {
            System.out.println("Out of memory once more");
        }

        nameText.setText(receivedCriminal.name);
        genderText.setText(receivedCriminal.gender);
        descriptionText.setText(receivedCriminal.description);
        ageText.setText(String.valueOf(receivedCriminal.age));
        bountyText.setText(String.valueOf(receivedCriminal.getBountyInDollars()));
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
}
