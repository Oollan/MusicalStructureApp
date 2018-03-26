package com.example.oollan.musicalstructureapp;

public class Song {

    private String songName;
    private String artistName;
    private String albumName;
    private static Song[] songs = new Song[100];

    private Song(String songName, String artistName, String albumName) {
        this.songName = songName;
        this.artistName = artistName;
        this.albumName = albumName;
    }

    public String getSongName() {
        return songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public static Song[] callList() {
        for (int i = 0; i < 100; i++) {
            songs[i] = new Song("Song #" + (i + 1), "Artist #" + (i + 1),
                    "Album #" + (i + 1));

        }
        return songs;
    }
}
