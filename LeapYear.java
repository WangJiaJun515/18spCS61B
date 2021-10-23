public class LeapYear{
    public static boolean isLeapYear(int year){
          if(year%4==0&&year%100!=0){
            return true;
          }
          else if(year%400==0){
            return true;
          }
          else{
            return false;
          }
    }
    public static void main(String[] Args){
        boolean judge = isLeapYear(Integer.parseInt(Args[0]));
        if(judge){
          System.out.println(Args[0] + " is a leap year.");
        }
        else{
          System.out.println(Args[0] + " is not a leap year.");
        }
    }

}
