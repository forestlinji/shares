package com.icecream.shares.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
  @TableId(type = IdType.AUTO)
  private Integer postId;
  private Integer releaseId;
  private Timestamp releaseTime;
  private String title;
  private String content;
  @JsonIgnore
  private Integer checkState;
  @TableLogic
  @JsonIgnore
  private Integer deleted;
  private String coverLink;
  private String picLink;
  private Integer beauty;
  private Integer price;
  private Integer type;
  private Integer quality;
  private Integer collectNum;
  private Integer goodNum;
  private Integer badNum;
}
