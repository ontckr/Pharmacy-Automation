package helper;

public class UserNameConverter {
	
	
	public static String Converter(String a) {
		 a = a.replaceAll(" ","");
		 
		    char[] turkishChars = new char[] {'ı', 'İ', 'ü', 'Ü', 'ö', 'Ö', 'ş', 'Ş', 'ç', 'Ç', 'ğ', 'Ğ'};
			char[] englishChars = new char[] {'i', 'I', 'u', 'U', 'o', 'O', 's', 'S', 'c', 'C', 'g', 'G'};
			for (int i = 0; i < turkishChars.length; i++) {
				a = a.replaceAll(new String(new char[]{turkishChars[i]}), new String(new char[]{englishChars[i]}));
			}
			a=a.toLowerCase();
		 System.out.println(a);
		return a;
		
	}
	
}
