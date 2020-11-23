package com.icecream.shares.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreferAdvice extends Prefer {
    public double sum;
    public double alike = 0;
    public Integer postId;

    public PreferAdvice(Prefer prefer) {
        this.setUserId(prefer.getUserId());
        this.setStudy(prefer.getStudy());
        this.setFood(prefer.getFood());
        this.setCloth(prefer.getCloth());
        this.setRoom(prefer.getRoom());
        this.setOthers(prefer.getOthers());
        sum = this.getStudy() + this.getFood() + this.getFood() + this.getCloth() + this.getRoom() + this.getOthers();
    }
}
