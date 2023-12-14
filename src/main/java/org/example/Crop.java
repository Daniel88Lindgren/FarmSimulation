

package org.example;



//Crop class extends Entity class
class Crop extends Entity{


    private int cropQuantity;


    public int getCropQuantity() {//Method to get crop quantity
        return cropQuantity;
    }


    //Constructor for id, name and quantity
    public Crop(int id, String name, int cropQuantity){
        super(id, name);
        this.cropQuantity = cropQuantity;
    }


    //Method to decrease quantity by 1 IF quantity is greater than 0
    public void decrementCropQuantity(){
        if (cropQuantity > 0){
            cropQuantity--;
        }
    }


    public void setCropQuantity(int quantity){//Method to set quantity to a new value
        this.cropQuantity = quantity;
    }


    //Method override form Entity to display crop info.
    @Override
    public String getDescription(){

        return "Id: "+ getId() + " <> Crop type: " + getName() + " <> Quantity: "+cropQuantity;
    }



}

