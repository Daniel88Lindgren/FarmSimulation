package org.example;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


 class Farm {

    String userInput;
    Scanner scanner = new Scanner(System.in);
     private ArrayList<Animal>animals;
     private ArrayList<Crop>crops;
     private int nextAnimalId;
     private int nextCropId;



    private void addAnimal(String name, String feedType){//Method to add animals to the farm when program starts
        Animal animal = new Animal(nextAnimalId , name, feedType);
        animals.add(animal);
        nextAnimalId++;//Generates new Id every new add
    }



    private void addAnimalByUser(String name,String feedType ){//Method so user can add animal. Name of animal input from menu
        boolean animalExists = false;

        for (Animal existingAnimal : animals){//Checking if new name of animal already exists on farm
            if (existingAnimal.getName().equals(name)){
                animalExists = true;
                break;
            }
        }
        if (animalExists){//If animal name is same as on farm, message and cancel adding
            System.out.println("That specie already exist on the farm! Adding animal denied");
        }
        else {//Animal name is unique , adding new animal to farm
            Animal animal = new Animal(nextAnimalId, name, feedType);
            animals.add(animal);
            nextAnimalId++;//Generates new Id every new add

        }


    }



    private void addCrop(String name, int cropQuantity){//Method to add crops when program starts
        Crop crop = new Crop(nextCropId, name, cropQuantity);
        crops.add(crop);
        nextCropId++;//Generates new Id every new add
    }



    private void addCropByUser(String name, int cropQuantity){//Method so user can add crops. Name and quantity input from menu

        boolean cropExists = false;
        for (Crop existingCrop : crops){//Checking if new name of crop already exists on the farm
            if (existingCrop.getName().equals(name)){
                cropExists = true;
                break;
            }
        }

        if (cropExists){//If the new name exists give user a second choice
            System.out.println("This crop already exists in the farm! Would you like to add more quantity instead? [yes/no]");
            String choice = scanner.next().toLowerCase();

            if (choice.equalsIgnoreCase("yes")){//User input "yes" quantity adjusted after users number input
                for (Crop existingCrop : crops){
                    if (existingCrop.getName().equals(name)){
                        existingCrop.setCropQuantity(existingCrop.getCropQuantity() + cropQuantity);
                        System.out.println("You added " + cropQuantity + " to " + name + " .");
                        return;
                    }
                }
            }
            else {
                System.out.println("No changes is made");//User input "no" canceling quantity add
            }
        }
        else {//Crop name is unique , adding new crop and quantity to farm
            Crop crop = new Crop(nextCropId, name, cropQuantity);
            crops.add(crop);
            nextCropId++;//Generates new Id every new add
        }


    }



    private void feedAnimal(String animalName){//Method so user can feed animal with crop. Input animal name then crop from menu
        Animal animalToFeed = null;
        String feedType = null;

        for (Animal animal : animals){//Checks if input animal name exists on farm.
            if (animal.getName().equals(animalName)){
                animalToFeed = animal;
                feedType = animal.getFeedType();
                break;
            }
        }
        if (animalToFeed == null){//If input animal name not exist, message and cancel.
            System.out.println("Animal not found");
        }
        else{
            for (Crop crop : crops){//Loops through crops to find matching feed type AND if the crop quantity is greater than 0, then message accepted feed
                if (crop.getName().equals(feedType) && crop.getCropQuantity() > 0){
                    crop.decrementCropQuantity();
                    System.out.println(animalName + " has been fed with "+feedType+". The animal is now well fed and happy!");
                    return;
                }
            }
            System.out.println("Not enough "+feedType+" to feed "+animalName+"\nTry fill upp the crop you want to feed the animal with. The " + animalName + " is getting really hungry!");//If requirement not met, message and cancel.
        }
    }



    private void removeCrop(String cropName){//Method so user can remove crops. Name input from menu
        Crop cropToRemove = null;

        for (Crop crop : crops){//Checks if input name exists on farm
            if (crop.getName().equals(cropName)){
                cropToRemove = crop;
                break;
            }
        }

        if (cropToRemove != null){//If name exists, crop removed from farm accepted
            crops.remove(cropToRemove);
            System.out.println("Crop: "+ cropName + " has been removed form the farm!");
        }
        else {//If name doesn't exist, message and cancel
            System.out.println(cropName + " could not been found on the farm. Please check spelling and right crop name and try again.");
        }
    }



    private void removeAnimal(String animalName){//Method so user can remove animals. Name input from menu
        Animal animalToRemove = null;

        for (Animal animal : animals) {//Check if input name exist on farm
            if (animal.getName().equals(animalName)) {
                animalToRemove = animal;
                break;
            }
        }

            if (animalToRemove != null){//If name exists, animal removed from farm accepted
                animals.remove(animalToRemove);
                System.out.println("Animal: "+animalName+" has been removes from the farm!");
            }
            else{//If name doesn't exist, message and cancel
                System.out.println(animalName + " could not been found at the farm. Please check spelling and right animal name and try again.");
            }

    }



    private void saveToFile(String farmData){//Method to save all animals and crops to one data txt file.

        try (FileWriter fileWriter = new FileWriter(farmData);//File writer for the specified file
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            bufferedWriter.write("Animals: ");//Main name for all animals
            bufferedWriter.newLine();

            for (Animal animal : animals){//Loops through animal list and write their info/values to the file
                bufferedWriter.write("Id: "+ animal.getId() + " -- Specie " + animal.getName() + " -- Eats: " + animal.getFeedType());
                bufferedWriter.newLine();
            }

            bufferedWriter.write("Crops: ");//Main name for all crops
            bufferedWriter.newLine();

            for (Crop crop : crops){//Loops through crop list and write their info/values to the file
                bufferedWriter.write("Id: " + crop.getId() + " -- Crop type: "+ crop.getName() + " -- Quantity: " + crop.getCropQuantity());
                bufferedWriter.newLine();
            }

            System.out.println("All data has been saved successfully! Farm register filename: "+ farmData);//Message when done

        }
        catch (IOException e){//If file not found program will not crash
            e.printStackTrace();
        }


    }



     public Farm(){//Constructor for farm class
         animals = new ArrayList<>();
         crops = new ArrayList<>();
         nextAnimalId = 1;
         nextCropId = 1;


     }



    public void setupFarm(){//Method to add pre-defined animals and crops

        addAnimal("cow", "grass");
        addAnimal("pig","all");
        addAnimal("chicken","corn");
        addAnimal("goat","corn");
        addAnimal("horse","carrot");
        addAnimal("duck","snail");

        addCrop("corn",1);
        addCrop("carrot",1);
        addCrop("snail",1);
        addCrop("grass",1);


    }



     public void menu(){//This is where the user will make all actions

        boolean backToMenu;

        System.out.println("Hi there and welcome to the amazing Farm Simulator!");


        do{

        System.out.println("\n\nEnter a number from the menu:\n[1]View crops" +
                                                            "\n[2]Remove crop" +
                                                            "\n[3]Add crop" +
                                                            "\n[4]View animals" +
                                                            "\n[5]Add animal" +
                                                            "\n[6]Feed animal" +
                                                            "\n[7]Remove animal" +
                                                            "\n[8]Save" +
                                                            "\n[10]Quit program");

            backToMenu = false;

            userInput = scanner.next();

            switch (userInput) {

                case "1":
                    System.out.println("All crops at the farm: \n");
                    for (Crop crop : crops) {
                        System.out.println(crop.getDescription());
                        System.out.println("------------------------------------------------");
                    }
                    break;
                case "2":
                    System.out.println("Enter the crop name you want to remove: ");
                    String removeCropByUser = scanner.next().toLowerCase();
                    removeCrop(removeCropByUser);
                    break;
                case "3":
                    System.out.println("Please enter crop name: ");
                    String cropName = scanner.next().toLowerCase();
                    try {
                        System.out.println("Enter amount of crops for " + cropName + " : ");
                        int cropQuantity = scanner.nextInt();
                        addCropByUser(cropName, cropQuantity);
                    }
                    catch(InputMismatchException e){
                        System.out.println("Numbers are only acceptable for quantity! Please try again");
                        scanner.next();
                    }
                    break;
                case "4":

                    System.out.println("All animals at the farm: \n");
                    for (Animal animal : animals) {
                        System.out.println(animal.getDescription());
                        System.out.println("------------------------------------------------");
                    }
                    break;
                case "5":
                    System.out.println("Please enter the specie of the animal: ");
                    String animalName = scanner.next().toLowerCase();
                    System.out.println("Now enter feed type of "+animalName+" : ");
                    String animalFeedType = scanner.next().toLowerCase();
                    addAnimalByUser(animalName, animalFeedType);
                    break;
                case "6":
                    System.out.println("What animal would you like to feed: ");
                    String animalUserFeed = scanner.next().toLowerCase();
                    feedAnimal(animalUserFeed);
                    break;
                case "7":
                    System.out.println("Please enter a animal you want to remove: ");
                    String removeAnimalByUser = scanner.next().toLowerCase();
                    removeAnimal(removeAnimalByUser);
                    break;
                case "8":saveToFile("farmData.txt");
                    break;
                case "10":backToMenu = true;
                    break;
                default:
                    System.out.println("You can only enter numbers from menu! ");
            }

        }while (! backToMenu);



    }




}
