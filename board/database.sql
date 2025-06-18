DROP TABLE IF EXISTS  tbl_board;

CREATE TABLE tbl_board (
   no			INTEGER AUTO_INCREMENT PRIMARY KEY,
   title		VARCHAR(200) NOT NULL,
   content		TEXT,
   writer		VARCHAR(50) NOT NULL,
   reg_date		DATETIME DEFAULT CURRENT_TIMESTAMP,
   update_date	DATETIME DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO tbl_board(title, content, writer)
VALUES
    ('테스트 제목1', '테스트 내용1', 'user00'),
    ('테스트 제목2', '테스트 내용2', 'user00'),
    ('테스트 제목3', '테스트 내용3', 'user00'),
    ('테스트 제목4', '테스트 내용4', 'user00'),
    ('테스트 제목5', '테스트 내용5', 'user00');


SELECT * FROM tbl_board;


DROP TABLE IF EXISTS tbl_board_attachment;

CREATE TABLE tbl_board_attachment (
      no INTEGER AUTO_INCREMENT PRIMARY KEY,
      filename VARCHAR(256) NOT NULL,	   -- 원본 파일 명
      path VARCHAR(256) NOT NULL,			-- 서버에서의 파일 경로
      content_type VARCHAR(56),				-- content-type
      size INTEGER,							-- 파일의 크기
      bno INTEGER NOT NULL,					-- 게시글 번호, FK
      reg_date DATETIME DEFAULT now(),
      CONSTRAINT FOREIGN KEY(bno) REFERENCES tbl_board(no)
);
