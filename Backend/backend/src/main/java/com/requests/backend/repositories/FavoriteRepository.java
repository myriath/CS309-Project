package com.requests.backend.repositories;

import com.requests.backend.models.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    @Query(value="SELECT * FROM fav_foods WHERE username = ?1", nativeQuery = true)
    Collection<Favorite> queryGetFavorites(String username);
}
