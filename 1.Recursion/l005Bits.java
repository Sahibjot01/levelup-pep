public class l005Bits {
    //question 1 off the ith bit
    static int off(int num, int idx){
        idx = idx%32;
        System.out.println(Integer.toBinaryString(num));
        int pow2 = 1;
        pow2 = (pow2<<idx);
        pow2 = ~(pow2);
        System.out.println(Integer.toBinaryString(num&pow2));
        return (num&pow2);
    }
    static int on(int num, int idx){
        idx = idx%32;
        System.out.println(Integer.toBinaryString(num));
        int pow2 = 1;
        pow2 = (pow2<<idx);
        System.out.println(Integer.toBinaryString(num|pow2));
        return (num|pow2);
    }
    public static void main(String ... args) {
       System.out.println(off(1421, 10));
       System.out.println(on(1421, 9));

    }
}
