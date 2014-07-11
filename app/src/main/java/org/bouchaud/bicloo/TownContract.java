package org.bouchaud.bicloo;

import org.json.JSONObject;

/**
 * Created by Erwan on 11/07/2014.
 */
public class TownContract {

  private String townContractName;


  public TownContract(JSONObject jsonStation) {
    this.townContractName = jsonStation.optString("contract");
  }


  public String getTownName() {
    return townContractName;
  }

  public void setTownName(String townContractName) {
    this.townContractName = townContractName;
  }
}
