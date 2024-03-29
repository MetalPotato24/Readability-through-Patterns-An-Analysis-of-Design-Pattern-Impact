package cs4015project;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Controller {
    public static void addSong(String title, Album album, String length, Label error, Stage popup){
        Song newSong = new Song(title, album.artistObjectProperty().get(), album.publishDateProperty.get(), length, album);
        HelloApplication.model.addSong(newSong);
        if(HelloApplication.model.result == -1){
            error.setTextFill(Color.color(1,0,0));
            error.setText("ERROR: Duplicate");
        }
        else{
            error.setText("");
            popup.close();
        }
    }

    public static void addAlbum(Artist artist, String albumTitle, String albumDate, String songTitle, String songDate, String songLength, Label error, Stage popup){

        Album album = new Album(albumTitle, albumDate, artist);
        HelloApplication.model.addAlbum(album);
        if(HelloApplication.model.result == -1){
            error.setText("ERROR: Duplicate");
        }
        else{
            error.setText("");
            popup.close();
        }

        Song song = new Song(songTitle, artist, songDate, songLength, album);
        HelloApplication.model.addSong(song);
        if(HelloApplication.model.result == -1){
            error.setText("ERROR: Duplicate");
        }
        else{
            error.setText("");
            popup.close();
        }
    }

    public static void addArtist(String name, String albumTitle, String albumDate, String songTitle, String songDate, String songLength, Label error, Stage popup){
        Artist artist = new Artist(name);
        HelloApplication.model.addArtist(artist);
        if(HelloApplication.model.result == -1){
            error.setText("ERROR: Duplicate");
        }
        else{
            error.setText("");
            popup.close();
        }

        Album album = new Album(albumTitle, albumDate, artist);
        HelloApplication.model.addAlbum(album);
        if(HelloApplication.model.result == -1){
            error.setText("ERROR: Duplicate");
        }
        else{
            error.setText("");
            popup.close();
        }

        Song song = new Song(songTitle, artist, songDate, songLength, album);
        HelloApplication.model.addSong(song);
        if(HelloApplication.model.result == -1){
            error.setText("ERROR: Duplicate");
        }
        else{
            error.setText("");
            popup.close();
        }
    }

    public static void editSong(Song song, String newTitle, String newDate, String newLength, Label error, Stage popup){
        HelloApplication.model.EditSong(song, newTitle,newDate,newLength);
        if(HelloApplication.model.result == -1){
            error.setText("ERROR: Duplicate");
        }
        else{
            error.setText("");
            popup.close();
        }
    }

}
