package org.scoula.ex03;

import java.util.ArrayList;
import java.util.List;

public class BoardDAO2 {

    public List<BoardVO2> getList(){
        List<BoardVO2> list = new ArrayList<BoardVO2>();
        for (int i = 0; i < 100; i++) {
                BoardVO2 vo = new BoardVO2(i,
                        "title" + i,
                        "content" + i,
                        "writer" + i);
                list.add(vo);
        }
        return list;
    }
}
