package com.dubbo.arithmetic;

/***
 *
 *
 * 描    述： 选择排序
 *
 * 创 建 者： liudh
 * 创建时间： 2017/10/23 13:40
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
public class SelectSort {

        public static void main(String[] args) {
            int[] arr={2,4,3,4,2};
            System.out.println("交换之前：");
            for(int num:arr){
                System.out.print(num+" ");
            }
            //选择排序的优化
            for(int i = 0; i < arr.length - 1; i++) {// 做第i趟排序
                int k = i;
                for(int j = k + 1; j < arr.length; j++){// 选最小的记录
                    if(arr[j] < arr[k]){
                        k = j; //记下目前找到的最小值所在的位置
                    }
                }
                //在内层循环结束，也就是找到本轮循环的最小的数以后，再进行交换
                if(i != k){  //交换a[i]和a[k]
                    int temp = arr[i];
                    arr[i] = arr[k];
                    arr[k] = temp;
                }
            }

            System.out.println();
            System.out.println("交换后：");
            for(int num:arr){
                System.out.print(num+" ");
            }
        }

}
