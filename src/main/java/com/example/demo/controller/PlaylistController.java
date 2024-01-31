package com.example.demo.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.demo.entity.Playlist;
import com.example.demo.entity.Song;
import com.example.demo.service.PlaylistService;
import com.example.demo.service.SongService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping; 
@Controller
public class PlaylistController 
{
	@Autowired
	SongService songService;
	@Autowired
	PlaylistService playlistservice;
	@GetMapping("/createPlaylist")
	public String createPlaylist(Model model) {
		List<Song> songList = songService.fetchAllSongs();
		model.addAttribute("songs", songList);
		return "createPlaylist";
	}
	@PostMapping("/addPlaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist) {
		playlistservice.addPlaylist(playlist);
		System.out.println(playlist);
		List<Song> songList = playlist.getSongs();
		for(Song s:songList)
		{
			s.getPlaylists().add(playlist);
			songService.updateSong(s);
		}
		return "adminHome";
	}
	@GetMapping("/viewPlaylists")
	public String viewPlaylists(Model model) {
		List<Playlist> allPlaylists=playlistservice.fetchAllPlaylists();
		model.addAttribute("allPlaylists", allPlaylists);
		return "displayPlaylist";
	}
	

}
  
  
  
  
