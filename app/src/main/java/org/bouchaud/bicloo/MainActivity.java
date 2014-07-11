package org.bouchaud.bicloo;


import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;

import org.bouchaud.bicloo.Session.BikeStationsListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity{

  private ScheduledExecutorService scheduleTaskExecutor;

  private MainActivity self = this;

  BikeStationListAdapter bikesAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    bikesAdapter = new BikeStationListAdapter(self, new ArrayList<BikeStation>());
    ListView listView = (ListView) findViewById(R.id.listView1);
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
