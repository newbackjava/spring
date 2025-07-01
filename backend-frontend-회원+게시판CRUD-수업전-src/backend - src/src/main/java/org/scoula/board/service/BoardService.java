package org.scoula.board.service;

import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.dto.BoardDTO;

import java.util.List;

public interface BoardService {
    List<BoardDTO> getList();

    BoardDTO get(Long no);

    BoardDTO create(BoardDTO board);

    BoardDTO update(BoardDTO board);

    BoardDTO delete(Long no);

    BoardAttachmentVO getAttachment(Long no);

    boolean deleteAttachment(Long no);

}
