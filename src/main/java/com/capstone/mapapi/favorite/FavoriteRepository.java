package com.capstone.mapapi.favorite;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    boolean existsByUserIdAndEndName(String userId, String endName);
    List<Favorite> findByUserId(String userId);
}