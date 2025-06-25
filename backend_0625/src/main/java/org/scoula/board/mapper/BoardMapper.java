package org.scoula.board.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.domain.BoardVO;
import org.scoula.common.page.PageRequest;

import java.util.List;

@Mapper
public interface BoardMapper {


    int getTotalCount();

    List<BoardVO> getPage(PageRequest pageRequest);

    //    @Select("select * from tbl_board order by no desc")
    List<BoardVO> getList();

    BoardVO get(Long no);

    void create(BoardVO board);

    int update(BoardVO board);

    int delete(Long no);

    // 첨부 파일 처리
    void createAttachment(BoardAttachmentVO attach);

    List<BoardAttachmentVO> getAttachmentList(Long bno);

    BoardAttachmentVO getAttachment(Long no);

    int deleteAttachment(Long no);

}
