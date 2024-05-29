package crud.spring.service;

import crud.spring.dto.BoardDTO;
import crud.spring.entity.BoardEntity;
import crud.spring.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();

        for (BoardEntity boardEntity : boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }

        return boardDTOList;
    }


    @Transactional // 트랜잭션이 필요하므로 꼭 붙여줘야함! (영속성 컨텍스트 --> 데이터 일관성 등)
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public BoardDTO findById(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            return BoardDTO.toBoardDTO(boardEntity);
        } else {
            return null;
        }
    }

    public BoardDTO update(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO);
        boardRepository.save(boardEntity);
        return findById(boardDTO.getId());
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    public Page<BoardDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 3; // 한 페이지에 보여줄 글 갯수
        // 한페이지당 3개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬
        Page<BoardEntity> boardEntities = boardRepository.findAll(PageRequest.of(page, pageLimit,
                                                        Sort.by(Sort.Direction.DESC, "id"))); // properties : entity 기준

        /*
        boardEntities.getContent(); //요청 페이지에 해당하는 글
        boardEntities.getTotalElements(); // 전체 글 갯수
        boardEntities.getNumber(); // DB로 요청한 페이지 번호
        boardEntities.getTotalPages(); // 전체 페이지 갯수
        boardEntities.getSize(); // 한 페이지에 보여지는 글 갯수
        boardEntities.hasPrevious(); // 이전 페이지 존재 여부
        boardEntities.isFirst(); // 첫 페이지 여부
        boardEntities.isLast(); // 마지막 페이지 여부
         */
        return boardEntities.map(board -> new BoardDTO(board.getId(), board.getBoardWriter(),
                                                                board.getBoardTitle(), board.getBoardHits(),
                                                                board.getCreatedTime()));
    }
}
