package eni.tp.app.eni_app.bo;

public class Movie {

    public long id;
    public String title;
    public int year;
    public int duration;
    public String synopsis;
    public String url;
    //Temporaire => Plus tard les notes = associations d'avis
    public int note = 2;

    public Movie(long id, String title, int year, int duration, String synopsis,String url) {
        this.id = id;
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
}
