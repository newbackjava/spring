package org.scoula.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.domain.BoardVO;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.mapper.BoardMapper;
import org.scoula.common.pagination.Page;
import org.scoula.common.pagination.PageRequest;
import org.scoula.common.util.UploadFiles;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final static String BASE_DIR = "c:/upload/board";
    final private BoardMapper mapper;

    @Override
    public List<BoardDTO> getList() {
        log.info("getList..........");

        return mapper.getList().stream()    // BoardVO의 스트림
                .map(BoardDTO::of)            // BoardDTO의 스트림
                .toList();                                // List<BoardDTO> 변환
    }

//    @Override
//    public void create(BoardDTO board) {
//        log.info("create......" + board);
//
//        BoardVO vo = board.toVo();
//        mapper.create(vo);
//        board.setNo(vo.getNo());
//    }

    @Override
    public BoardDTO get(Long no) {
        log.info("get......" + no);

        BoardDTO board = BoardDTO.of(mapper.get(no));
        return Optional
                .ofNullable(board)
                .orElseThrow(NoSuchElementException::new);

    }

    // 2개 이상의 insert 문이 실행될 수 있으므로 트랜잭션 처리 필요
    // RuntimeException인 경우만 자동 rollback.
    @Transactional
    @Override
    public BoardDTO create(BoardDTO board) {
        log.info("create......" + board);

        BoardVO boardVO = board.toVo();
        mapper.create(boardVO);

        // 파일 업로드 처리
        List<MultipartFile> files = board.getFiles();
        if (files != null && !files.isEmpty()) {    // 첨부 파일이 있는 경우
            upload(boardVO.getNo(), files);
        }

        return get(boardVO.getNo());
    }

    private void upload(Long bno, List<MultipartFile> files) {
        for (MultipartFile part : files) {
            if (part.isEmpty()) continue;
            try {
                String uploadPath = UploadFiles.upload(BASE_DIR, part);
                BoardAttachmentVO attach = BoardAttachmentVO.of(part, bno, uploadPath);
                mapper.createAttachment(attach);
            } catch (IOException e) {
                throw new RuntimeException(e);   // @Transactional에서 감지, 자동 rollback
            }
        }
    }


    @Override
    public BoardDTO update(BoardDTO board) {
        log.info("update......" + board);
        mapper.update(board.toVo());

        // 파일 업로드 처리
        List<MultipartFile> files = board.getFiles();
        if (files != null && !files.isEmpty()) {
            upload(board.getNo(), files);
        }


        return get(board.getNo());
    }

    @Override
    public BoardDTO delete(Long no) {
        log.info("delete...." + no);
        BoardDTO board = get(no);

        mapper.delete(no);
        return board;
    }

    // 첨부파일 한 개 얻기
    @Override
    public BoardAttachmentVO getAttachment(Long no) {
        return mapper.getAttachment(no);
    }

    // 첨부파일 삭제
    @Override
    public boolean deleteAttachment(Long no) {
        return mapper.deleteAttachment(no) == 1;
    }

    @Override
    public Page<BoardDTO> getPage(PageRequest pageRequest) {

        //특정한 페이지번호에 해당하는 게시물 리스트를 Page객체로 만들어주어야함.
        //Page객체안에는 List<BoardDTO>, amount, totalCount
        List<BoardVO> list = mapper.getPage(pageRequest); //특정한 페이지에 대한 목록
        int totalCount = mapper.getTotalCount(); //전체 게시물의 수

        //vue에 여러가지 값을 넣어서 응답할 예정
        //Page객체 사용할 예정임.
        //Page<BoardDTO> page = new Page(list, totalCount,.....);
        //해당 입력 필드로 초기화를 시켜서 객체하고 싶을 때 사용

        //db에서 가지고 결과로 page수를 계산해서 Page객체로 만들어야함. of()함수를 만들었음.
        Page<BoardDTO> page = Page.of(pageRequest, totalCount,
                list.stream().map(BoardDTO::of).toList());
        return page;
    }
}











