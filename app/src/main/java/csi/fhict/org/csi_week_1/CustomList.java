package csi.fhict.org.csi_week_1;

/**
 * Created by Nekkyou on 10-3-2016.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomList extends ArrayAdapter<Criminal>{

    private final Activity context;
    private final List<Criminal> criminals;
//    private final String[] web;
//    private final Integer[] imageId;
//    public CustomList(Activity context,
//                      String[] web, Integer[] imageId) {
//        super(context, R.layout.list_single, web);
//        this.context = context;
//        this.web = web;
//        this.imageId = imageId;
//
//    }

    public CustomList(Activity context,
                      List<Criminal> criminals) {
        super(context, R.layout.list_single, criminals);
        this.context = context;
        this.criminals = criminals;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        TextView bountyText = (TextView) rowView.findViewById(R.id.SingleBounty);

        Criminal requestedCriminal = criminals.get(position);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText("Name: " + requestedCriminal.name);
        bountyText.setText("Bounty: " + String.valueOf(requestedCriminal.getBountyInDollars()));

//        imageView.setImageResource(imageId[position]);
//        imageView.setImageDrawable(requestedCriminal.mugshot);
        imageView.setImageResource(requestedCriminal.imageID);
        return rowView;
    }
}