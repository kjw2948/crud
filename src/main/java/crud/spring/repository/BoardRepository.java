package crud.spring.repository;

import crud.spring.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// JpaRepository -->  Entity 클래스와 Entity 클래스의 pk 타입

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}
