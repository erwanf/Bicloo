package org.bouchaud.bicloo;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
    super(context, R.layout.town_contract_row);
    this.context = context;
    this.townContracts = new ArrayList<TownContract>(objects);
    this.mInflater = LayoutInflater.from(context);
  }

  public ArrayList<TownContract> getTownContracts() {
    return townContracts;
  }

  public void setBikes(ArrayList<TownContract> townContracts) {
    this.townContracts = townContracts;
  }




  @Override
  public int getCount() {
    return townContracts.size();
  }

  @Override
  public TownContract getItem(int position) {
    return townContracts.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public int getPosition(BikeStation item) {
    return townContracts.indexOf(item);
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

    BikeStation bikeStation = townContracts.get(position);
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
