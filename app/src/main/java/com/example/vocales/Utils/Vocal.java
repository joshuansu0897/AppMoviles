package com.example.vocales.Utils;

public class Vocal {
    private String image;
    private String sound;
    private String vocal;

    public Vocal() {
    }

    public Vocal(String image, String sound, String vocal) {
        this.image = image;
        this.sound = sound;
        this.vocal = vocal;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getVocal() {
        return vocal;
    }

    public void setVocal(String vocal) {
        this.vocal = vocal;
    }
}
