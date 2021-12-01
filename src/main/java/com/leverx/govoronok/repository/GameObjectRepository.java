package com.leverx.govoronok.repository;

import com.leverx.govoronok.model.GameObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GameObjectRepository extends JpaRepository<GameObject, Long> {

    List<GameObject> getGameObjectsByAuthor_Id(Long id);
}
