package crud.spring.dto;


//DTO --> 어떤 데이터의 한세트를 묶어서 DTO 클래스를 만들어서 데이터를 전송할 때 사용한다.

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@ToString
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
@NoArgsConstructor // 기본생성자

public class BoardDTO {
    //field는 제한 두기
    private Long id;
    private String boardWriter; //작성자 이름
    private String boardPass; // 작성자 비밀번호
    private String boardTitle; // 제목
    private String boardContents; // 내용

    private int boardHits; // 조회수
    private LocalDateTime boardCreatedTime; //글 작성시간
    private LocalDateTime boardUpdateTime; // 글 수정시간
}
