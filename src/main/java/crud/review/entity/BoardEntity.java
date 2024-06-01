package crud.review.entity;

import crud.review.dto.BoardDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter @Setter
@Entity
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //PK

    private String boardTitle;
    private String boardPass;
    private String boardWriter;
    private String boardContents;
    private LocalDateTime boardCreatedTime;
    private LocalDateTime boardUpdatedTime;

    public BoardEntity toEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();

        //값 전달
        boardEntity.setId(boardDTO.getId());
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardCreatedTime(boardDTO.getBoardCreatedTime());
        boardEntity.setBoardUpdatedTime(boardDTO.getBoardUpdatedTime());

        return boardEntity;
    }
}
