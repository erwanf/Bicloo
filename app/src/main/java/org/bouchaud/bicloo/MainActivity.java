package org.bouchaud.bicloo;


import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;

import org.bouchaud.bicloo.Session.BikeStationsListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity{

  public final static String M_name = "org.bouchaud.Bicloo.name";
  public final static String M_adr = "org.bouchaud.Bicloo.adr";
  public final static String M_freeBike = "org.bouchaud.Bicloo.freeBike";
  public final static String M_freeSlot = "org.bouchaud.Bicloo.freeSlot";
  public final static String M_totalSlot = "org.bouchaud.Bicloo.totalSlot";

  private MainActivity self = this;

  BikeStationListAdapter bikesAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    bikesAdapter = new BikeStationListAdapter(self, new ArrayList<BikeStation>());
    ListView listView = (ListView) findViewById(R.id.listView1);
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(MainActivity.this, DetailItem.class);
        i.putExtra(M_name, "Name");
        i.putExtra(M_adr, "This is my address");
        i.putExtra(M_freeBike, 15);
        i.putExtra(M_totalSlot, 20);
        i.putExtra(M_freeSlot, 5);
        startActivity(i);
      }
    });
    listView.setAdapter(bikesAdapter);

    this.askRefresh();

    Button refreshButton = (Button) findViewById(R.id.refreshButton);
    refreshButton.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        self.askRefresh();
      }
    });
  }

  private void askRefresh(){
    Session.getInstance(self).getBikeStations(new BikeStationsListener() {

      @Override
      public void bikeStationsUpdated(ArrayList<BikeStation> bikeStation) {
        bikesAdapter.setBikes(bikeStation);
        bikesAdapter.notifyDataSetChanged();
      }
    });
  }
}
