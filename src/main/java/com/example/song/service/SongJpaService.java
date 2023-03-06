 package com.example.song.service;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.stereotype.Service;
 import org.springframework.web.server.ResponseStatusException;
 import com.example.song.repository.*;
 import org.springframework.web.bind.annotation.*;
import com.sun.xml.bind.annotation.OverrideAnnotationOf;
import com.example.song.model.*;
 import org.springframework.data.jpa.repository.JpaRepository;
 import java.util.*;

 @Service
 public class SongJpaService implements SongRepository {
    @Autowired
    public SongJpaRepository songJpaRepository;

    @Override
    public ArrayList<Song> getSongs(){
        List<Song> songList = songJpaRepository.findAll();
        ArrayList<Song> songs = new ArrayList<>(songList);
        return songs;
    }

    @Override
    public Song getSongById(int songId){
        try{
            Song song = songJpaRepository.findById(songId).get();
            return song;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Song addSong(Song song){
        try{
            songJpaRepository.save(song);
            return song;
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public Song updateSong(int songId,Song song){
        try{
            Song newSong = songJpaRepository.findById(songId).get();
            if(song.getSongName() != null){
                newSong.setSongName(song.getSongName());
            }
            if(song.getLyricist() != null){
                newSong.setLyricist(song.getLyricist());
            }
            if(song.getSinger() != null){
                newSong.setSinger(song.getSinger());
            }
            if(song.getMusicDirector() != null){
                newSong.setMusicDirector(song.getMusicDirector());
            }
            songJpaRepository.save(newSong);
            return newSong;
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        
    }

   @Override
    public void deleteSong(int songId){
        try{
            songJpaRepository.deleteById(songId);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    
 }
 

// Write your code here