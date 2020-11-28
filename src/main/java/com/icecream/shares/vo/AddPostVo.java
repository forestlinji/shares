package com.icecream.shares.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddPostVo {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotNull
    private MultipartFile images;
    @Min(value = 1)
    @Max(value = 10)
    private Integer beauty;
    @Min(value = 1)
    @Max(value = 10)
    private Integer price;
    @Min(value = 0)
    @Max(value = 4)
    private Integer type;
    @Min(value = 1)
    @Max(value = 10)
    private Integer quality;

}
