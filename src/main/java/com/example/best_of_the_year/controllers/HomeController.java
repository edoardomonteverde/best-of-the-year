/* Ciao a tutti/e @qui
Esercitazione di oggi: Spring MVC - Best of the year
Nome repo: best-of-the-year
Le modalità di consegna sono le solite, con repo github da associare alla cartella di progetto java.
Cosa fare?

Step 1
Creare un nuovo progetto Spring Boot MVC + Thymeleaf (vedi tutorial sulle slide).
Il nome del progetto è best-of-the-year (stesso nome del repo).
Nel progetto aggiungere un controller che risponde alla root dell’applicazione, con un metodo che restituisce una view fatta con Thymeleaf in cui viene stampato un titolo: “Best of the year by …” (al posto dei puntini deve apparire il vostro nome, passato come attributo dal controller attraverso il Model).

Step 2

Creare all’interno del controller due metodi privati :
- uno restituisce una lista di oggetti di tipo Movie - getBestMovies()
- l’altro restituisce una lista di  oggetti di tipo Song - getBestSongs()

Creare le classi Movie e Song aventi almeno :
- un id
- un titolo
Aggiungere al controller altri due metodi, che rispondono agli url
- “/movies”
- “/songs”
In ognuno di questi metodi aggiungere al Model un attributo stringa con una lista di titoli di migliori film o canzoni (in base al metodo che stiamo implementando) separati da virgole.
Utilizzare i due metodi getBest… per recuperare i film e le canzoni.
Creare i rispettivi template Thymeleaf.
Creare due metodi
- “/movies/{id}”
- “/songs/{id}”
che dato il parametro id passato tramite il path, mostri in pagina il titolo relativo al film / canzone. */
package com.example.best_of_the_year.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.best_of_the_year.models.Movie;
import com.example.best_of_the_year.models.Song;



@Controller
public class HomeController {

    @GetMapping("/")
    public String home (
        @RequestParam(name="name", defaultValue = "Anonymous") String name, Model model
    ){
        model.addAttribute("name", name);
        return "home";
    }

    private List<Movie> getBestMovies() {
    // Creazione degli oggetti Movie
    Movie movie1 = new Movie(1, "Inception");
    Movie movie2 = new Movie(2, "The Dark Knight");
    Movie movie3 = new Movie(3, "Interstellar");

    // Creazione della lista e aggiunta degli oggetti
    List<Movie> movies = new ArrayList<>();
    movies.add(movie1);
    movies.add(movie2);
    movies.add(movie3);

    return movies;
}

    private List<Song> getBestSongs() {
    // Creazione degli oggetti Song
    Song song1 = new Song(1, "Bohemian Rhapsody");
    Song song2 = new Song(2, "Imagine");
    Song song3 = new Song(3, "Hotel California");

    // Creazione della lista e aggiunta degli oggetti
    List<Song> songs = new ArrayList<>();
    songs.add(song1);
    songs.add(song2);
    songs.add(song3);

    return songs;
}

 // Metodo per mostrare i titoli dei migliori film
 @GetMapping("/movies")
 public String movies(Model model) {
     List<Movie> movies = getBestMovies();
     model.addAttribute("movies", movies);
     return "movies";
}

@GetMapping("/songs")
public String songs(Model model) {
    List<Song> songs = getBestSongs();
    model.addAttribute("songs", songs);
    return "songs";
}

@GetMapping("/movies/{id}")
public String movieDetails(@PathVariable int id, Model model) {
    Movie foundMovie = null;

    // Cerca il film corrispondente all'id
    for (Movie movie : getBestMovies()) {
        if (movie.getId() == id) {
            foundMovie = movie;
            break;
        }
    }

    // Aggiungi il film trovato (o null se non esiste) al modello
    model.addAttribute("movie", foundMovie);

    // Ritorna il nome del template
    return "movie-details";
}
@GetMapping("/songs/{id}")
public String songDetails(@PathVariable int id, Model model) {
    Song foundSong = null;

    // Cerca la canzone corrispondente all'id
    for (Song song : getBestSongs()) {
        if (song.getId() == id) {
            foundSong = song;
            break;
        }
    }

    // Aggiungi la canzone trovata (o null se non esiste) al modello
    model.addAttribute("song", foundSong);

    // Ritorna il nome del template
    return "song-details";
}

}


