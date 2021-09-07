import com.opencsv.bean.CsvBindByName;

import java.util.ArrayList;
import java.util.List;

//@JsonPropertyOrder({"id", "title","score", "year", "duration", "rating", "budget", "genres", "gross", "director", "actor1", "actor2", "actor3", "language", "country"})
public class ImdbDto {

    @CsvBindByName(column = "id")
    private int id;

    @CsvBindByName(column = "title")
    private String title;

    @CsvBindByName(column = "score")
    private double score;

    @CsvBindByName(column = "year")
    private int year;

    @CsvBindByName(column = "duration")
    private int duration;

    @CsvBindByName(column = "rating")
    private String rating;

    @CsvBindByName(column = "budget")
    private long budget;

    @CsvBindByName(column = "genres")
    private String genresUnsplit;

    private List<String> genres = new ArrayList<>();


    @CsvBindByName(column = "gross")
    private long gross;

    @CsvBindByName(column = "director")
    private String director;

    @CsvBindByName(column = "actor1")
    private String actor1;

    @CsvBindByName(column = "actor2")
    private String actor2;

    @CsvBindByName(column = "actor3")
    private String actor3;

    @CsvBindByName(column = "language")
    private String language;

    @CsvBindByName(column = "country")
    private String country;

    private List<String> actors = new ArrayList<>();


    public List<String> getActors() {
        if(!getActor1().isEmpty() && !getActor2().isEmpty() && !getActor3().isEmpty()){
            actors.add(getActor1());
            actors.add(getActor2());
            actors.add(getActor3());
            return  actors;
        }else{
            return null;
        }


    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public double getScore() {
        return score;
    }

    public int getYear() {
        return year;
    }

    public int getDuration() {
        return duration;
    }

    public String getRating() {
        return rating;
    }

    public long getBudget() {
        return budget;
    }

    public String getGenresUnsplit() {
        return genresUnsplit;
    }

    public List<String> getGenres() {
        if(getGenresUnsplit() != ""){
        String[] temp = getGenresUnsplit().split("\\|");
        genres = List.of(temp);

        return genres;}
        else{
            return null;
        }
    }

    public long getGross() {
        return gross;
    }

    public String getDirector() {
        return director;
    }

    public String getActor1() {
        return actor1;
    }

    public String getActor2() {
        return actor2;
    }

    public String getActor3() {
        return actor3;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }



    @Override
    public String toString() {
        return "ImdbDto{" +
                "title='" + title + '\'' +
                ", score=" + score +
                ", year=" + year +
                ", duration=" + duration +
                ", rating='" + rating + '\'' +
                ", budget=" + budget +
                ", genres=" + genres +
                ", gross=" + gross +
                ", director='" + director + '\'' +
                ", actor1='" + actor1 + '\'' +
                ", actor2='" + actor2 + '\'' +
                ", actor3='" + actor3 + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
