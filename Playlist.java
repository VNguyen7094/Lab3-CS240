import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.ArrayList;

public class Playlist {
    private Queue<Song> playlist;
    private Song currentSong;
    private ArrayList<Album> albums;

    public Playlist() {
        playlist = new LinkedList<>();
        currentSong = null;
        albums = new ArrayList<>();
    }

    public void addAlbum(Album album) {
        albums.add(album);
    }

    public boolean addSongToPlaylist(Song song) {
        for (Album album : albums) {
            if (album.getSongs().contains(song)) {
                playlist.add(song);
                return true;
            }
        }
        System.out.println("The song does not appear on any album!");
        return false;
    }

    public void skipBackwards() {
        if (currentSong != null && playlist.size() > 1) {
            Song lastSong = null;
            for (Song song : playlist) {
                lastSong = song;
            }
            System.out.println("Now playing: " + lastSong.getTitle());
            playlist.add(currentSong);  // Readd current song to the queue
            currentSong = lastSong;
        } else {
            System.out.println("You are at the start of the playlist.");
        }
    }

    public void skipForwards() {
        if (!playlist.isEmpty()) {
            currentSong = playlist.poll();  // Remove the first song 
            System.out.println("Now playing: " + currentSong.getTitle());
            if (!playlist.isEmpty()) {
                playlist.offer(currentSong);  // Add it back to the end of the queue
            }
        } else {
            System.out.println("Playlist is empty.");
        }
    }

    public void replayCurrentSong() {
        if (currentSong != null) {
            System.out.println("Replaying: " + currentSong.getTitle());
        } else {
            System.out.println("No songs are currently performing.");
        }
    }

    public void listSongsInPlaylist() {
        if (playlist.isEmpty()) {
            System.out.println("There are no songs in your playlist.");
        } else {
            System.out.println("Songs in Playlist:");
            for (Song song : playlist) {
                System.out.println(song);
            }
        }
    }

    public void removeCurrentSong() {
        if (currentSong != null) {
            playlist.remove(currentSong);
            System.out.println("Song removed from playlist.");
            if (!playlist.isEmpty()) {
                currentSong = playlist.peek();  // Get the next song from the front
            } else {
                currentSong = null;
            }
        } else {
            System.out.println("No song to remove.");
        }
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {
            System.out.println("\n--- Playlist Menu ---");
            System.out.println("1. Add Song ");
            System.out.println("2. Skip Backward");
            System.out.println("3. Skip Forwards");
            System.out.println("4. Replay Current Song");
            System.out.println("5. List All Songs in Playlist");
            System.out.println("6. Remove Current Song from Playlist");
            System.out.println("7. Quit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.println("Choose a song to add from available albums:");
                    for (Album album : albums) {
                        System.out.println(album.getAlbumName() + ": ");
                        for (Song song : album.getSongs()) {
                            System.out.println(song);
                        }
                    }
                    System.out.print("Enter the song title to add: ");
                    String title = scanner.nextLine();
                    boolean songAdded = false;
                    for (Album album : albums) {
                        for (Song song : album.getSongs()) {
                            if (song.getTitle().equals(title)) {
                                songAdded = addSongToPlaylist(song);
                                break;
                            }
                        }
                    }
                    if (!songAdded) {
                        System.out.println("Song not found in any album.");
                    }
                    break;

                case 2:
                    skipBackwards();
                    break;

                case 3:
                    skipForwards();
                    break;

                case 4:
                    replayCurrentSong();
                    break;

                case 5:
                    listSongsInPlaylist();
                    break;

                case 6:
                    removeCurrentSong();
                    break;

                case 7:
                    quit = true;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }

        scanner.close();
    }
}

