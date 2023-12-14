package org.example;


//Base class for entities on farm
 class Entity {

    public int id;
    protected String name;


    //Constructor for id and name
    public Entity(int id, String name){
        this.id = id;
        this.name = name;
    }


    public int getId(){//Getter method for Id
        return id;
    }


    public String getName(){//Getter method for name
        return name;
    }


    public String getDescription(){//Method to display id and name values
        return "Id: "+ id + " Name: " + name;
    }


}
