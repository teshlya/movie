package data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListMoviesData {
    @SerializedName("results")
    ArrayList<MovieData> movies;

    @SerializedName("total_pages")
    int totalPages;

    public ArrayList<MovieData> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<MovieData> movies) {
        this.movies = movies;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
