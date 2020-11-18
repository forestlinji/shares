package com.icecream.shares.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostOperation {
    @TableId(type = IdType.AUTO)
    private Integer operationId;
    private Integer operatorId;
    private Integer postId;
    private Integer operationType;
    private Timestamp operationTime;



}
