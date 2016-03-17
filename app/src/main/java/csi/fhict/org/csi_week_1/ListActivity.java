package csi.fhict.org.csi_week_1;

import android.app.Application;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    ListView list;
    String[] web;
    Integer[] imageID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        CriminalProvider cp = new CriminalProvider(getApplicationContext());
        final List<Criminal> criminals = cp.GetCriminals();

//        setWebAndImages();

        CustomList adapter = new
                CustomList(ListActivity.this, criminals);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                goToNextScreen(criminals.get(+position));

            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public void goToNextScreen(Criminal criminal) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("criminal", criminal);
        startActivity(intent);
    }

    public void setWebAndImages() {
        web = getResources().getStringArray(R.array.criminalList);
        imageID = getResourceID();
    }


    public Integer[] getResourceID() {
        Resources res = getResources();
        TypedArray images = res.obtainTypedArray(R.array.criminalsImageList);

        Integer[] returnvalue = new Integer[images.length()];
        for (int i=0; i < images.length(); i++) {
            returnvalue[i] = images.getResourceId(i, 0);
        }


        return returnvalue;
    }
}
