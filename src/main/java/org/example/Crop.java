

package org.example;




class Crop extends Entity{//Crop class extends Entity class

    private static int nextCropId = 1;//Animal id will start with 1 instead of 0
    private int cropQuantity;


    public int getCropQuantity() {//Method to get crop quantity
        return cropQuantity;
    }


    public Crop(int id, String name, int cropQuantity){//Constructor for id, name and quantity
        super(id, name);
        this.cropQuantity = cropQuantity;
        //nextCropId++;//Increment the static id for next crop
    }


    public void decrementCropQuantity(){//Method to decrease quantity by 1 IF quantity is greater than 0
        if (cropQuantity > 0){
            cropQuantity--;
        }
    }


    public void setCropQuantity(int quantity){//Method to set quantity to a new value
        this.cropQuantity = quantity;
    }


    @Override
    public String getDescription(){//Method override form Entity to display crop info.

        return "Id: "+ getId() + " <> Crop type: " + getName() + " <> Quantity: "+cropQuantity;
    }



}

