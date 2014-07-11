package org.bouchaud.bicloo;

import org.json.JSONObject;

public class BikeStation {

  private String name;
  private int    bikeStands;
  private int    availableBikesStands;

  public BikeStation(JSONObject jsonStation) {
    this.name = jsonStation.optString("name");
    this.bikeStands = jsonStation.optInt("bike_stands");
    this.availableBikesStands = jsonStation.optInt("available_bike_stands");
  }

  public String getName() {
    return name;
  }

  public int getBikeStands() {
    return bikeStands;
  }

  public int getAvailableBikesStands() {
    return availableBikesStands;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setBikeStands(int bikeStands) {
    this.bikeStands = bikeStands;
  }

  public void setAvailableBikesStands(int availableBikesStands) {
    this.availableBikesStands = availableBikesStands;
  }
}