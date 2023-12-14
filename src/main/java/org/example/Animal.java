

package org.example;


//Animal class extends Entity class
class Animal extends Entity {
    private String feedType;



    //Constructor for animal id , name and type of food they eat.
    public Animal(int id, String name, String feedType){
        super(id, name);
        this.feedType = feedType;
    }


    public String getFeedType(){//Method to get feed type of animal
        return feedType;
    }


    //Method override form Entity to display animal info.
    @Override
    public String getDescription(){
        return "Id: "+ getId() + " <> Specie: " + getName() + " <> Eats: "+feedType;
    }


}
