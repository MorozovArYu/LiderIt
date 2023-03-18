package com.example.liderit.repository;

import com.example.liderit.models.Player;
import com.example.liderit.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface PlayerRepository extends JpaRepository<Player,Integer> {


}
