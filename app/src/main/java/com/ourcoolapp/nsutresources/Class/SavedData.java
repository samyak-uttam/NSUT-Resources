package com.ourcoolapp.nsutresources.Class;

public class SavedData {

    private String dataName;

    private String dataType;

    public SavedData(String name, String dataType) {
        this.dataName = name;
        this.dataType = dataType;
    }

    public String getDataName() {
        return dataName;
    }

    public String getDataType() {
        return dataType;
    }
}
