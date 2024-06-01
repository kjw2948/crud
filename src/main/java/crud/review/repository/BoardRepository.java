package crud.review.repository;

import crud.spring.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    void updateHits(Long id);
}
