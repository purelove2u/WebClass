package domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardVO {
    private int bno;
    private String name;
    private String password;
    private String title;
    private String content;
    private String attach;
    private int re_ref;
    private int re_lev;
    private int re_seq;
    private int readcount;
    private Date regdate;
}
