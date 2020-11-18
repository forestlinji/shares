package com.icecream.shares.pojo;



import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
  @TableId
  private long postId;
  private long releaseId;
  private java.sql.Timestamp releaseTime;
  private String title;
  private String content;
  @JsonIgnore
  private long checkState;
  @TableLogic
  @JsonIgnore
  private long deleted;
  private String coverLink;
  private String picLink;
  private long beauty;
  private long price;
  private long type;
  private long quality;
  private long collectNum;
  private long goodNum;
  private long badNum;


}
