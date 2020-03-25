package com.amandamcnair.testingassign2;

public class Food {
    public int id;
    public String description;
    public String dataType;
    public String foodCode;

    public Food() {

    }

    public Food(int id, String description, String dataType, String foodCode) {
        this.id = id;
        this.description = description;
        this.dataType = dataType;
        this.foodCode = foodCode;
    }

    public Food(int id, String description, String dataType) {
        this.id = id;
        this.description = description;
        this.dataType = dataType;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getDataType() {
        return dataType;
    }

    public String getFoodCode() {
        return foodCode;
    }




    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public void setFoodCode(String foodCode) {
        this.foodCode = foodCode;
    }
}
