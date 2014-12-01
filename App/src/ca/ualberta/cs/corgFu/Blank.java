package ca.ualberta.cs.corgFu;

/**
 * This is a utility class checks whether the input by the 
 * usr contains consecutive blanks.  
 * @author Anthony
 */

public class Blank {
	
	/**
	 * Evaluates whether the usr input (as a string) only contains 
	 * consecutive blank characters. 
	 * @return The a Boolean indicating whether the string is
	 * blank or not. 
	 */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }
	
}
