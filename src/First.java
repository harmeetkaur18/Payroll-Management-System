public class First {
           public static void main(String[] args)
           {
        	   System.out.println("Hi Harmeet");
        	   try {
        		   Class.forName("com.mysql.cj.jdbc.Driver");
        		   System.out.println("Database Connected.");
        		   
        	   }catch(Exception e)
        	   {
        		   System.out.println("Could not connect database");
        	   }
           }
}
