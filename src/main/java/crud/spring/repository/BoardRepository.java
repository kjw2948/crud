package crud.spring.repository;

import crud.spring.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


// JpaRepository -->  Entity 클래스와 Entity 클래스의 pk 타입

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    //update board_table set board_hits=board_hits + 1 where id = ?
    @Modifying  // update, delete 등의 쿼리를 실행할 때는 해당쿼리를 필수로 붙이기!
    @Query(value = "update BoardEntity b set b.boardHits = b.boardHits+1 where b.id = :id")
    //JPA에서 제공하는 어노테이션
    void updateHits(@Param("id") Long id);
}
