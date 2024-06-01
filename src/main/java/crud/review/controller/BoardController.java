package crud.review.controller;

import crud.review.dto.BoardDTO;
import crud.review.repository.BoardRepository;
import crud.spring.entity.BoardEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardRepository boardRepository;

    @GetMapping("/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO, Model model) {
        model.addAttribute("board", boardDTO);
        return "save";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        boardRepository.updateHits(id);
        Optional<BoardEntity> board = boardRepository.findById(id);
        model.addAttribute("board", board);
        return "detail";
    }
}
