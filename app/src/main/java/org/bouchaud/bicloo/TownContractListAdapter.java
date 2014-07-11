package org.bouchaud.bicloo;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erwan on 11/07/2014.
 */
public class TownContractListAdapter  extends ArrayAdapter<TownContract> {


  private ArrayList<TownContract> townContracts;

  private Context context;
  private LayoutInflater mInflater;
  private boolean mNotifyOnChange = true;


  public TownContractListAdapter(Context context, List<BikeStation> objects) {
    super(context, R.layout.bike_station_row);
    this.context = context;
    this.townContracts = new ArrayList<TownContract>(objects);
    this.mInflater = LayoutInflater.from(context);
  }
}
