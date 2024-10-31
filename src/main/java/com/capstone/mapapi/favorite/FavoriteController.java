package com.capstone.mapapi.favorite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private FavoriteRepository favoriteRepository;


    @PostMapping ("/save")
    public Favorite saveFavorite(@RequestBody Favorite favorite) {
        return favoriteService.saveFavorite(favorite);

    }

    // 중복 확인 API
    @PostMapping("/check-duplicate")
    public ResponseEntity<Boolean> checkDuplicateFavorite(@RequestBody Favorite favorite) {
        boolean isDuplicate = favoriteService.isDuplicate(favorite.getUserId(), favorite.getEndName());
        return ResponseEntity.ok(isDuplicate);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Favorite>> getFavoritesByUserId(@PathVariable String userId) {
        List<Favorite> favorites = favoriteRepository.findByUserId(userId);
        return ResponseEntity.ok(favorites);
    }

    // 즐겨찾기 삭제 메서드 추가
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavorite(@PathVariable Long id) {
        boolean isDeleted = favoriteService.deleteFavorite(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build(); // 성공적으로 삭제된 경우
        } else {
            return ResponseEntity.notFound().build(); // 해당 ID가 존재하지 않을 경우
        }
    }
}