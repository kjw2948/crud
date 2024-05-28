package crud.review.repository;

import crud.review.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    /*
    JpaRepository 상속 받아서 기본 기능 사용  findById 등등
    게시판 조회수 증가시키는 로직
     */
}
