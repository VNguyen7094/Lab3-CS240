public class Main {
    public static void main(String[] args) {
        // Create some songs
        Song song1 = new Song("Song One", 200);
        Song song2 = new Song("Song Two", 240);
        Song song3 = new Song("Song Three", 180);
        Song song4 = new Song("Song Four", 210);

        // Create albums
        Album album1 = new Album("Album 1");
        album1.addSong(song1);
        album1.addSong(song2);

        Album album2 = new Album("Album 2");
        album2.addSong(song3);
        album2.addSong(song4);

        // Create the playlist
        Playlist playlist = new Playlist();
        playlist.addAlbum(album1);
        playlist.addAlbum(album2);

        // Display the menu and interact with the user
        playlist.displayMenu();
    }
}