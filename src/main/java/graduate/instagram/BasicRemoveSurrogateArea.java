package graduate.instagram;

public class BasicRemoveSurrogateArea implements RemoveSurrogateArea {

	public String getRemoveSurrogateArea(String string) {
		// TODO Auto-generated method stub
		 if ( string == null ) return null;
		  
		  StringBuffer buf = new StringBuffer();
		  int len = string.length();
		  for ( int i = 0 ; i < len ; i++ )
		  {
		   char c = string.charAt(i);
		   if ( 0xD800 <= c && c <= 0xDBFF || 
		     0xDC00 <= c && c <= 0xDFFF )
		   {
		   }
		   else
		   {
		    buf.append(c);
		   }
		  }
		  return buf.toString();
	}

}
