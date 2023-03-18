package com.example.liderit.repository;

import com.example.liderit.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface TeamRepository extends JpaRepository<Team,Integer> {
    List<Team> findAllBySportKind(String sportKind);
    List<Team> findAllByCreationDateBetween(LocalDate startDate, LocalDate endDate);

}
