package crud.spring.repository;

import crud.spring.dto.BoardDTO;
import crud.spring.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


// JpaRepository -->  Entity 클래스와 Entity 클래스의 pk 타입

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    //update board_table set board_hits=board_hits + 1 where id = ?  ==> @Query에 nativeQuery = true 설정 넣어주면 쿼리문 자체를
    //입력 가능 하기도 함
    @Modifying  // update, delete 등의 쿼리를 실행할 때는 해당쿼리를 필수로 붙이기!
    @Query(value = "update BoardEntity b set b.boardHits = b.boardHits+1 where b.id = :id")
    //JPA에서 제공하는 어노테이션
    void updateHits(@Param("id") Long id); // @Param에 있는 "id"가 위에 쿼리문에 있는 id와 매칭된다고 생각하면 됨

}
