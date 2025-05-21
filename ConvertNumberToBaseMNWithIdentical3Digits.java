public class ConvertNumberToBaseMNWithIdentical3Digits {
    public static void main(String[] args) {
        for(int i=2;i<=100000000;i++){
            int count = 0;
            for(int j=2;j<i;j++){
                String res = convertToBaseN(i,j);
                if(res.length()==3 && allIdentical(res)){
                    System.out.println(i+" "+j+" "+res);
                    count++;
                }
                if(count>=2){
                    System.out.println(i+" "+j+" "+res+" "+count);
                    break;
                }
            }
            if(count>=2){
                    break;
            }
        }
    }

    public static boolean allIdentical(String str){
        char ch = str.charAt(0);
        for(int i=1;i<str.length();i++){
            if(str.charAt(i)!=ch){
                return false;
            }
        }
        return true;
    }

    public static String convertToBaseN(int number, int base) {
        String result = "";
        while(number>0){
            int remainder = number % base;
            result = remainder + result;
            number = number / base;
        }
        return result;
    }
}
