package com.example.liderit.repository;

import com.example.liderit.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;


@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    @Query(nativeQuery = true,
            value = "select * from players where team_id = :teamId and role = :role ")
    List<Player> getPlayersFromTeamByTeamIdFilteredByRole(@Param("teamId")Integer teamId, @Param("role")String role);


}
