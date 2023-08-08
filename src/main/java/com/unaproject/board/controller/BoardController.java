package com.unaproject.board.controller;

import com.unaproject.board.dto.BoardDTO;
import com.unaproject.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board") //대표주소
public class BoardController {
    private final BoardService boardService; //생성자 의존성 주입

    //게시글 작성
    @GetMapping("/save")  //그이하의 주소 , /board/save와 같음
    public String saveForm() {
        return "save";
    }

    //게시글 작성 완료
    @PostMapping("/save") //같은메서드와 같은주소 쓸수없음,get post 구분, 오류발생
    public String save(@ModelAttribute BoardDTO boardDTO) {
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return "redirect:/board/paging";
    }

    //게시글 목록
    @GetMapping("/")
    public String findAll(Model model){
        //DB에서 전체 게시글 데이터를 가져와서 list.html에 보여준다
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "list";
    }

    //게시글 상세조회
    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model,
                           @PageableDefault(page = 1) Pageable pageable) {
        /*
            해당 게시글의 조회수를 하나 올리고
            게시글 데이터를 가져와서 detail.html에 출력
         */
        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        model.addAttribute("page", pageable.getPageNumber());
        return "detail";
    }

    //게시글 수정
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model){
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("boardUpdate", boardDTO);
        return "update";
    }

    //게시글 수정 완료
    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
        BoardDTO board = boardService.update(boardDTO);
        model.addAttribute("board", board);
        return "redirect:/board/paging";
//      return "redirect:/board/" + boardDTO.getId(); 조회수가 같이 반영되서 사용x
    }

//    //게시글 삭제
//    @GetMapping("/delete/{id}")
//    public String delete(@PathVariable Long id){
//        boardService.delete(id);
//        return "redirect:/board/paging";
//    }

    //게시글 삭제
    @GetMapping("/delete/{id}")
    public String deleteForm(@PathVariable Long id, Model model) {
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("boardDelete", boardDTO);
        // Add the post ID as a model attribute to be used in the form
        model.addAttribute("postId", id);
        return "delete";
    }

    // 게시글 삭제완료
    @PostMapping("/delete")
    public String delete(@RequestParam Long postId) {
        boardService.delete(postId);
        return "redirect:/board/paging";
    }

    //페이징 처리
    // /board/paging?page=1
    @GetMapping("/paging")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
        //PageableDefault(page = 1) : 페이지값이 없더라도 1이라고 페이지값을 지정해 사용하겠다
        //Pageable : 파라미터에서 page라고 지정되어있는 파라미터값을 받아줌
        //getPageNumber : 몇페이지가 요청됬는지 값을 사용 할 수 있음
        //        pageable.getPageNumber();
        Page<BoardDTO> boardList = boardService.paging(pageable);
        int blockLimit = 3; //하단에 보여지는 페이지 갯수
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < boardList.getTotalPages()) ? startPage + blockLimit - 1 : boardList.getTotalPages();

        // page 갯수 20개
        // 현재 사용자가 3페이지
        // 1 2 3
        // 현재 사용자가 7페이지
        // 7 8 9
        // 보여지는 페이지 갯수 3개
        // 총 페이지 갯수 8개

         /* startPage : 현재 사용자가 1 or 2 or 3 페이지에 있으면 1값을 만들어 주는 것
	                  7 or 8 or 9 페이지에 있으면 7값을 만들어 주는 것
	                  현재사용자가 요청한 페이지를 blockLimit으로 나눠서 소수점을 올리는 처리에서 -1을 빼고 blockLimit값에 1을 더해 곱함 */

        /* endPage : 총 페이지 갯수가 8개라면 하단에 7,8만 보여줘야함 9페이지 보여주면 안됨
	                 9보다 실제 전체페이지 갯수가 작은 값을 가지고 있다면 9값을 보여주지 말고 전체페이지 값을 endPage값으로 해야 함
	                 삼항연산자 , 조건만족하면 첫번째 실행 아니면 두번쨰 실행 */


        model.addAttribute("boardList", boardList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "paging";

    }



}
