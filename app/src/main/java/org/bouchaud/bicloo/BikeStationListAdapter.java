package org.bouchaud.bicloo;


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class BikeStationListAdapter extends ArrayAdapter<BikeStation> {

  private Context context;
  private ArrayList<BikeStation> bikes;

  private LayoutInflater mInflater;
  private boolean mNotifyOnChange = true;

  public BikeStationListAdapter(Context context, List<BikeStation> objects) {
    super(context, R.layout.bike_station_row);
    this.context = context;
    this.bikes = new ArrayList<BikeStation>(objects);
    this.mInflater = LayoutInflater.from(context);
  }

  public ArrayList<BikeStation> getBikes() {
    return bikes;
  }

  public void setBikes(ArrayList<BikeStation> bikes) {
    this.bikes = bikes;
  }




  @Override
  public int getCount() {
    return bikes.size();
  }

  @Override
  public BikeStation getItem(int position) {
    return bikes.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public int getPosition(BikeStation item) {
    return bikes.indexOf(item);
  }

  @Override
  public int getViewTypeCount() {
    return 1; //Number of types + 1 !!!!!!!!
  }

  @Override
  public int getItemViewType(int position) {
    return 1;
  }


  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    final ViewHolder holder;
    int type = getItemViewType(position);
    if (convertView == null) {
      holder = new ViewHolder();
      switch (type) {
        case 1:
          convertView = mInflater.inflate(R.layout.bike_station_row, parent, false);
          holder.name = (TextView) convertView.findViewById(R.id.textView_name);
          holder.description = (TextView) convertView.findViewById(R.id.textView_description);
          break;
      }
      convertView.setTag(holder);
    } else {
      holder = (ViewHolder) convertView.getTag();
    }

    BikeStation bikeStation = bikes.get(position);
    float full = (float) Math.min(((float)bikeStation.getAvailableBikesStands() / bikeStation.getBikeStands()) + 0.3, 1.0);

    convertView.setBackgroundColor(Color.rgb(255, Math.round(255 * full), Math.round(255 * full)));
    holder.name.setText(bikeStation.getName());
    holder.description.setText(bikeStation.getAvailableBikesStands() + "/" +bikeStation.getBikeStands());
    holder.pos = position;
    return convertView;
  }

  @Override
  public void notifyDataSetChanged() {
    super.notifyDataSetChanged();
    mNotifyOnChange = true;
  }

  public void setNotifyOnChange(boolean notifyOnChange) {
    mNotifyOnChange = notifyOnChange;
  }


  //---------------static views for each row-----------//
  static class ViewHolder {

    TextView name;
    TextView description;
    int pos; //to store the position of the item within the list
  }
}
