package org.bouchaud.bicloo;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

public class Session {

  public static interface BikeStationsListener{
    public void bikeStationsUpdated(ArrayList<BikeStation> bikeStation);
  }

  public static interface TowncontractsListener{
    public void TownContractsUpdated(ArrayList<TownContract> townContracts);
  }

  private static Session singleton;

  private Context context;

  private ArrayList<BikeStation> bikeStations;
  private ArrayList<TownContract> townContracts;


  private Session(){
    bikeStations = new ArrayList<BikeStation>();
    townContracts = new ArrayList<TownContract>();
  }

  public static Session getInstance(Context context){
    if(singleton == null){
      singleton = new Session();
    }
    singleton.context = context;
    return singleton;
  }

  public void getBikeStations(final BikeStationsListener listener){

    String url = "https://api.jcdecaux.com/vls/v1/stations?contract=Nantes&apiKey=4805fd402d6772c551772e3828e722c0ed01a0cb";

    RequestQueue queue = Volley.newRequestQueue(context);

    JsonArrayRequest jsObjRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
      @Override
      public void onResponse(JSONArray array) {

        bikeStations.clear();
        for (int i = 0; i < array.length(); i++) {
          JSONObject row = array.optJSONObject(i);
          bikeStations.add(new BikeStation(row));
        }

        Collections.sort(bikeStations, new Comparator<BikeStation>(){
          public int compare(BikeStation b1, BikeStation b2) {
            return b1.getName().compareToIgnoreCase(b2.getName());
          }
        });
        listener.bikeStationsUpdated(bikeStations);
      }
    }, new Response.ErrorListener() {

      @Override
      public void onErrorResponse(VolleyError error) {

        Log.d("xxx", error.toString());
      }
    });

    queue.add(jsObjRequest);
  }


  public void getTownContracts(final TowncontractsListener listener){

    String url = "https://api.jcdecaux.com/vls/v1/contracts?apiKey=8140bf73d8f086144a4b183680fb82704a8049c1";

    RequestQueue queue = Volley.newRequestQueue(context);

    JsonArrayRequest jsObjRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
      @Override
      public void onResponse(JSONArray array) {

        townContracts.clear();
        for (int i = 0; i < array.length(); i++) {
          JSONObject row = array.optJSONObject(i);
          townContracts.add(new TownContract(row));
        }

        Collections.sort(townContracts, new Comparator<TownContract>(){
          public int compare(TownContract t1, TownContract t2) {
            return t1.getTownName().compareToIgnoreCase(t2.getTownName());
          }
        });
        listener.TownContractsUpdated(townContracts);
      }
    }, new Response.ErrorListener() {

      @Override
      public void onErrorResponse(VolleyError error) {

        Log.d("xxx", error.toString());
      }
    });

    queue.add(jsObjRequest);
  }


}
