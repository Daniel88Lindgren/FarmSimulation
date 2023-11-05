

package org.example;

import java.util.ArrayList;

class Animal extends Entity {//Animal class extends Entity class
    private String feedType;
    private static int nextAnimalId = 1;//Anmial Id will start with 1 insead of 0


    public Animal(int id, String name, String feedType){//Constructor for animal Id , name and type of food they eat.
        super(id, name);
        this.feedType = feedType;
        nextAnimalId++;//Increment the static Id for next animal
    }


    public String getFeedType(){//Method to get feed type of animal
        return feedType;
    }


    @Override
    public String getDescription(){//Method override form Entity to display animal info.

        return "Id: "+ getId() + " <> Specie: " + getName() + " <> Eats: "+feedType;

    }


}
