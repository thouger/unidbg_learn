package com.so_learn.lession9;

public class utils {
    public static void main(String[] args) {
        System.out.println("test");
        System.out.println("result:"+sub_37A4(1632325280, 26));
    }

    public static int sub_37A4(int a1, int a2){
        int a1_eor_a2 = a1 ^ a2;
        if ( a2 == 1 )
        {
            if ( (a1_eor_a2 ^ a1) < 0 )
                a1 = -a1;
        }else {
            int temp = a1;
            if ( a1 < 0 )
                temp = -a1;
            if ( temp <= a2 )                             // 如果input1 不大于 input2
            {
                if ( temp < a2 )
                    a1 = 0;
                if ( temp == a2 )
                    a1 = (a1_eor_a2 >> 31) | 1;
            }else if ( (a2 & (a2 - 1)) != 0 ){
                int v5 = __clz(a2) - __clz(temp);
                int v6 = a2 << v5;
                int v7 = 1 << v5;
                a1 = 0;
                while (true)
                {
                    if ( temp >= v6 )
                    {
                        temp -= v6;
                        a1 |= v7;
                    }
                    if ( temp >= v6 >> 1 )
                    {
                        temp -= v6 >> 1;
                        a1 |= v7 >> 1;
                    }
                    if ( temp >= v6 >> 2 )
                    {
                        temp -= v6 >> 2;
                        a1 |= v7 >> 2;
                    }
                    if ( temp >= v6 >> 3 )
                    {
                        temp -= v6 >> 3;
                        a1 |= v7 >> 3;
                    }
                    Boolean v8 = temp == 0;
                    if (temp!=0)
                    {
                        v7 >>= 4;
                        v8 = v7 == 0;
                    }
                    if ( v8 )
                        break;
                    v6 >>= 4;
                }
                if ( a1_eor_a2 < 0 ){
                    a1 = -a1;
                }
            }
            else
            {
                a1 = temp >> (31 - __clz(a2));
                if ( a1_eor_a2 < 0 )
                    a1 = -a1;
            }
        }
        return a1;
    }

    public static int __clz(int x)
    {
        // Keep shifting x by one until leftmost bit
        // does not become 1.
        int total_bits = 32;
        int res = 0;
        while ((x & (1 << (total_bits - 1))) == 0)
        {
            x = (x << 1);
            res++;
        }

        return res;
    }
}
