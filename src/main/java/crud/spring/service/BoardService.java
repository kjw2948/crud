package crud.spring.service;

import crud.spring.dto.BoardDTO;
import crud.spring.entity.BoardEntity;
import crud.spring.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// DTO -> Entity (Entity Class에서 진행)
// Entity -> DTO (DTO Class에서 진행)
// 등의 일들이 Service에서 빈번히 일어남  DTO(Controller 에게 주거나, 받을 때) , Entity(Repository에게 주거나, 받을때)
//Entity 클래스는 최대한 Service 계층까지만 사용하도록 하는게 좋음 그래서 DTO로 변환하는 과정이 Service에서 나타남

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
    }
}
