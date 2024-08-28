package eni.tp.app.eni_app.bo;

import jakarta.validation.constraints.*;

public class Movie {

    public long id;

    @NotBlank(message = "le titre doit être renseigné")
    @Size(min=1, max=250, message = "Doit avoir au moins 1 caractère")
    public String title;

    @Positive
    @Min(value=1895, message = "vous devez rentrer une année valide")
    public int year;

    @Positive(message = "vous devez rentrer une durée valide (au moins 1 min)")
    @Max(value=1000, message = "vous devez rentrer une durée valide (<1000 min)")
    public int duration;

    @NotBlank(message = "vous devez rentrer un synopsis valide")
    @Size(min=30, max=400, message = "Doit avoir au moins 30 caractères")
    public String synopsis;

    @NotBlank(message = "l''url doit être renseigné")
    public String url;
    //Temporaire => Plus tard les notes = associations d'avis

    public int note = 2;

    public Movie(){

    }
    public Movie(long id, String title, int year, int duration, String synopsis,String url) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.synopsis = synopsis;
        this.url = url;
    }
    public Movie (String title, int year, int duration, String synopsis,String url){
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.synopsis = synopsis;
        this.url = url;

    }

    //plus tard quand on va supprimer le int note, la note sera la moyenne des avis
    public int getNote(){
        return note;
    }

    public void setNote(int note){
        this.note= note;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
