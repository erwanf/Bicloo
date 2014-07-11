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


  private ArrayList<TownContract> towns;

  private Context context;
  private LayoutInflater mInflater;
  private boolean mNotifyOnChange = true;


  public TownContractListAdapter(Context context, List<TownContract> objects) {
    super(context, R.layout.town_contract_row);
    this.context = context;
    this.towns = new ArrayList<TownContract>(objects);
    this.mInflater = LayoutInflater.from(context);
  }

  public ArrayList<TownContract> getTownContracts() { return towns;  }

  public void setTowns(ArrayList<TownContract> townContracts) {
    this.towns = townContracts;
  }


  @Override
  public int getCount() {
    return towns.size();
  }

  @Override
  public TownContract getItem(int position) {
    return towns.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public int getPosition(TownContract item) {
    return towns.indexOf(item);
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
          convertView = mInflater.inflate(R.layout.town_contract_row, parent, false);
          holder.name = (TextView) convertView.findViewById(R.id.textView_name);
          break;
      }
      convertView.setTag(holder);
    } else {
      holder = (ViewHolder) convertView.getTag();
    }

    TownContract townContract = towns.get(position);

    holder.name.setText(townContract.getTownName());

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
    int pos; //to store the position of the item within the list
  }


}
