package lyk.community.dto;

import lombok.Data;
import lyk.community.model.User;

@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModify;
    private Integer viewCount;
    private Integer creator;
    private Integer likeCount;
    private Integer commentCount;
    private User user;
}
