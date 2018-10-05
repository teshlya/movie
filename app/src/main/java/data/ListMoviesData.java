package data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListMoviesData {
    @SerializedName("results")
    ArrayList<MovieData> movies;

    public ArrayList<MovieData> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<MovieData> movies) {
        this.movies = movies;
    }
}
