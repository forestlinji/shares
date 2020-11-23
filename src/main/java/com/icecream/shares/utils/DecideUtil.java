package com.icecream.shares.utils;

import Jama.Matrix;
import com.icecream.shares.pojo.Post;
import com.icecream.shares.vo.DecideVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dqbryant
 * @create 2020/11/22 17:19
 */
public class DecideUtil {
    public static double change(int value){
        switch (value){
            case 1: return 4;
            case 2: return 2;
            case 4: return 0.5;
            case 5: return 0.25;
            default: return 1;
        }
    }
    public static List<Double> decide(List<Post> posts, DecideVo decideVo){
        /**
         * 第一行美观,第二行价格,第三行质量
         */
        Matrix matrix = new Matrix(3, 3);
        matrix.set(0, 0, 1.0);
        matrix.set(1, 1, 1.0);
        matrix.set(2, 2, 1.0);
        double value1 = DecideUtil.change(decideVo.getCriterion1());
        double value2 = DecideUtil.change(decideVo.getCriterion2());
        double value3 = value2 / value1;
        matrix.set(0, 1, value1);
        matrix.set(1, 0, 1 / value1);
        matrix.set(0, 2, value2);
        matrix.set(2, 0, 1 / value2);
        matrix.set(1, 2, value3);
        matrix.set(2, 1, 1 / value3);
        int size = decideVo.getPostIds().size();
        Matrix matrix1 = new Matrix(size, size);
        List<Integer> list1 = new ArrayList<>();
        for(Post post: posts){
            list1.add(post.getBeauty());
        }
        for(int i = 0;i < size;i++) {
            for (int j = 0; j < size; j++) {
                matrix1.set(i, j, 1.0 * list1.get(i) / list1.get(j));
            }
        }
        Matrix matrix2 = new Matrix(size, size);
        List<Integer> list2 = new ArrayList<>();
        for(Post post: posts){
            list2.add(post.getPrice());
        }
        for(int i = 0;i < size;i++){
            for(int j = 0;j < size;j++){
                matrix2.set(i, j, 1.0 * list2.get(i) / list2.get(j));
            }
        }
        Matrix matrix3 = new Matrix(size, size);
        List<Integer> list3 = new ArrayList<>();
        for(Post post: posts){
            list3.add(post.getQuality());
        }
        for(int i = 0;i < size;i++){
            for(int j = 0;j < size;j++){
                matrix3.set(i, j, 1.0 * list3.get(i) / list3.get(j));
            }
        }
        Matrix matrix4 = new Matrix(size, 3);
        double l = size % 2 == 0?size / 2.0 -1:Math.floor(size / 2.0);
        for(int i=0;i < size;i++){
            matrix4.set(i, 0, matrix1.eig().getV().get(i, (int)l));
            matrix4.set(i, 1, matrix2.eig().getV().get(i, (int)l));
            matrix4.set(i, 2, matrix3.eig().getV().get(i, (int)l));
        }
        Matrix result = matrix4.times(matrix);
        List<Double> vector = new ArrayList<>();
        for(int i = 0;i < size;i++){
            vector.add(result.get(i, 0));
        }
        return vector;
    }
}
