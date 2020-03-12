package lyk.community.model;

import lombok.Data;

@Data
public class Question {
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

}
