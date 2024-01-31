package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.*;
public interface SongRepository extends JpaRepository<Song, Integer>
{

	public Song findByname(String name);

}