package com.calleridname.calldetailcallhistory.sim_information_activity;

public class Sim_Model {
    Sim_Model datamodels1;
    int image;
    String name;
    String[] titles;

    public Sim_Model getDatamodels1() {
        return this.datamodels1;
    }

    public void setDatamodels1(Sim_Model sim_Model) {
        this.datamodels1 = sim_Model;
    }

    public String[] getTitles() {
        return this.titles;
    }

    public void setTitles(String[] strArr) {
        this.titles = strArr;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getImage() {
        return this.image;
    }

    public void setImage(int i) {
        this.image = i;
    }
}
