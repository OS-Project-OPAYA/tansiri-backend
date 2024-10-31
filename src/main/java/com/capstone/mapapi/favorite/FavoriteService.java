package com.capstone.mapapi.favorite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    public Favorite saveFavorite(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    // 중복 여부 확인
    public boolean isDuplicate(String userId, String endName) {
        return favoriteRepository.existsByUserIdAndEndName(userId, endName);
    }

    // 즐겨찾기 삭제 메서드 추가
    public boolean deleteFavorite(long id) {
        if (favoriteRepository.existsById(id)) {
            favoriteRepository.deleteById(id);
            return true; // 삭제 성공
        }
        return false; // ID가 존재하지 않음
    }
}