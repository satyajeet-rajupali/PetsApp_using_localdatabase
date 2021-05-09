package com.satyajeet.pets.Data;

public class PetData {

    private String petName;
    private String petBreed;

    public PetData(String petName, String petBreed) {
        this.petName = petName;
        this.petBreed = petBreed;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }
}
