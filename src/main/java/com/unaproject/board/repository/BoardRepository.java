package com.unaproject.board.repository;

import com.unaproject.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//repository 클래스는 entity 클래스만 받아준다
public interface BoardRepository extends JpaRepository<BoardEntity, Long>  {
    // update board_table set board_hits=board_hits+1 where id=?
    //Boardentity 약어 b 사용
    //:id는 param id와 매칭, 같게함
    @Modifying //update, delete 쿼리 실행할 때 추가적으로 Modifying 어노테이션 필수
    @Query(value = "update BoardEntity b set b.boardHits=b.boardHits+1 where b.id=:id")
    void updateHits(@Param("id") Long id);

}
