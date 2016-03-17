package csi.fhict.org.csi_week_1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Nekkyou on 17-3-2016.
 */
public class CrimeListAdapter extends ArrayAdapter<Crime>{
    private final Activity context;
    private final List<Crime> crimes;

    public CrimeListAdapter(Activity context,
                      List<Crime> crimes) {
        super(context, R.layout.crimelistitem, crimes);
        this.context = context;
        this.crimes = crimes;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.crimelistitem, null, true);

        TextView crimeName = (TextView) rowView.findViewById(R.id.crimeName);
        TextView crimeBounty = (TextView) rowView.findViewById(R.id.crimeBounty);

        final Crime currentCrime = crimes.get(position);

        crimeName.setText(currentCrime.name);
        crimeBounty.setText(String.valueOf(currentCrime.bountyInDollars));

        rowView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(v.getContext(), currentCrime.description, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        return rowView;
    }
}
