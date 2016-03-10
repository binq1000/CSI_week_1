package csi.fhict.org.csi_week_1;

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

public class ListActivity extends AppCompatActivity {

    ListView list;
    String[] web;
    Integer[] imageID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        setWebAndImages();
        CustomList adapter = new
                CustomList(ListActivity.this, web, imageID);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(ListActivity.this, "You Clicked at " + web[+position], Toast.LENGTH_SHORT).show();

            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public void setWebAndImages() {
        web = getResources().getStringArray(R.array.criminalList);
        imageID = getResourceID();
    }


    public Integer[] getResourceID() {
        Resources res = getResources();
        TypedArray images = res.obtainTypedArray(R.array.criminalsImageList2);

        Integer[] returnvalue = new Integer[images.length()];
        for (int i=0; i <= images.length(); i++) {
            returnvalue[i] = images.getResourceId(i, 0);
        }


        return returnvalue;
    }
}
