package com.dubbo.arithmetic;

/***
 *
 *
 * 描    述： 二分查找法
 *
 * 创 建 者： liudh
 * 创建时间： 2017/10/23 10:55
 * 创建描述：
 *
 * 修 改 者：  
 * 修改时间： 
 * 修改描述： 
 *
 * 审 核 者：
 * 审核时间：
 * 审核描述：
 *
 */
public class BinaryFind {

//    算法：请找出数组中的某个数，它的左侧数字相加之和等于右边。
//
//    原 算法：请找出数组中的某个数，它的左侧数字相加之和等于右边。
//
//
//    摘要: 二分查找的思想在算法面试题目中的使用，阿里算法面试题目：请找出数组中的某个数，它的左侧数字相加之和等于右边。
//
//    看到这题，我的第一个想法就是从头到尾遍历数组，然后计算左边之和，右边之和，比较它们。实现看起来是这样的：

    /**
     * 傻逼遍历法
     *
     * @param array
     * @return
     */
    public static int stuFind(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int totalLeft = 0;
            for (int le = 0; le < i; le++) {
                totalLeft += array[le];
            }
            int totalRight = 0;
            for (int ri = i + 1; ri < array.length; ri++) {
                totalRight += array[ri];
            }
            if (totalLeft == totalRight) {
                return i;
            }
        }
        return -1;
    }

//    既然是阿里的算法面试，当然不会这么轻易的放过你，楼上的遍历时间复杂度应该是n^2.其实还可以优化一下，
// 反正是顺序后移，计算可以偷懒一下，当你第一次计算左边的值和右边的值以后，第二次移动，左边的值等于原先
// 左边的值+当前位置前一个位置的值，右边的值等于原先右边的值减去当前的位置的数字的值。代码看起来是这样的：

    /**
     * 傻逼遍历法二
     *
     * @param array
     * @return
     */
    public static int stuFind1(int[] array) {
        int totalLeft = 0;
        int totalRight = 0;
        for (int i = 1; i < array.length; i++) {
            if (i == 1) {
                for (int le = 0; le < i; le++) {
                    totalLeft += array[le];
                }
                for (int ri = i + 1; ri < array.length; ri++) {
                    totalRight += array[ri];
                }
            } else {
                totalLeft += array[i - 1];
                totalRight -= array[i];
            }

            if (totalLeft == totalRight) {
                return i;
            }
        }
        return -1;
    }

//    从头到尾的遍历，暂时想不出来更好的办法了，换个思路：既然某个数字的左边的值等于右边，那么可以算出数组的全部数值，
//    然后加入一个二分查找的办法，定位到中间，如果左边的值*2=数组的和-当前位置的值，那么就可以说找到了，如果大于，那就向前移动，
//    小于就向后移动。（为什么要左边的值*2，而不是（数组的和-当前位置的值)/2，欢迎你评论 ：)，算法的实现看起来是这样的：

    /**
     * 二分查找法
     *
     * @param array
     * @return
     */
    public static int splitFind(int[] array) {
        if (null == array || array.length == 0) {
            return -1;
        }
        int length = array.length;
        int head, tall, index;
        head = 0;
        tall = length - 1;
        index = length / 2;
        int total = 0;
        for (int i : array) {
            total += i;
        }
        do {
            int totalLeft = 0;
            for (int le = 0; le < index; le++) {
                totalLeft += array[le];
            }
            int doubleValue = (total - array[index]);
            if (totalLeft * 2 < doubleValue) {
                head = index;
                index = index + (length - index) / 2;
            } else if (totalLeft * 2 > doubleValue) {
                tall = index;
                index = head + (index - head) / 2;
            } else {
                return index;
            }
        } while (index > head && index < tall);
        return -1;
    }


    public static void main(String[] args) {
        int arr[] = {2, 4, 3, 4, 2, 7};
        int i = splitFind(arr);
        System.out.println(i);
    }


}
