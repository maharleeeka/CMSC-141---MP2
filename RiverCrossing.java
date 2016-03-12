import java.io.*;
import java.util.*;

class RiverCrossing{
		

	public static void main(String[] args) {
		String fileName = "mp2.in";

		ArrayList <String> WEST = new ArrayList();
		ArrayList <String> EAST = new ArrayList();
        
		try{
		FileReader fileReader = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;

        File file = new File("mp2.out");
        file.createNewFile();        
        FileWriter writer = new FileWriter(file);
	
			while((line = bufferedReader.readLine()) != null){
				WEST.add("R");
        		WEST.add("C");
        		WEST.add("L");
        		WEST.add("N");

				for(int i = 1; i <= line.length();){
					if(deadCompany(WEST)){
						break;
					}
					if(deadCompany(EAST)){
						break;
					}
					else{
						if(i % 2 == 1){
							if(withMan(WEST)){
								if(!(ifExists(line.substring(i - 1,i),EAST))){
									
									if(line.substring(i - 1,i).equals("R")){
										toEast("R", WEST, EAST);}
									else if(line.substring(i - 1,i).equals("C")){
										toEast("C", WEST, EAST);}
									else if(line.substring(i -1,i).equals("L")){
										toEast("L", WEST, EAST);}
									else if(line.substring(i-1,i).equals("N")){
										EAST.add("N");
										WEST.remove("N");
									}
									i++;
								}
								else{
									i++;}
							}
							else{
								break;
							}
						}
						else if(i % 2 == 0){
							if(withMan(EAST)){
								if(!(ifExists(line.substring(i - 1,i),WEST))){
									
									if(line.substring(i - 1,i).equals("R")){
										toWest("R", EAST, WEST);}
									else if(line.substring(i -1,i).equals("C")){
										toWest("C", EAST, WEST);}
									else if(line.substring(i -1,i).equals("L")){
										toWest("L", EAST, WEST);}
									else if(line.substring(i-1,i).equals("N")){
										WEST.add("N");
										EAST.remove("N");
									}
									i++;
								}
								else{
									i++;}
							}
							else{
								break;
							}
						}
					}
					// System.out.print(WEST + " ");
					// System.out.println(EAST);

				}
				if (EAST.size() == 4) {
					writer.write(line + ": " + "OK" + "\n");
					System.out.println(line);
				}
				else{
					writer.write(line + ": " + "NG" + "\n");	
				}

				EAST.clear();
				WEST.clear();
			}
				writer.flush();
				writer.close();

		}catch(FileNotFoundException ex){
			System.out.println("Unable to open " + "'" + fileName + "'");
		}catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
	}

	//checks if a character (N,R,L,C) is in a certain bank, if true, then the character can move to other bank
	static boolean ifExists(String a, ArrayList <String> b){
		for (String letter : b) {
	     if (letter.equals(a)) {
	            return true;
	        }
	    }
	    return false;
	}
	//performs the movement of R,L,C
	static void toEast(String a, ArrayList <String> b, ArrayList<String> c) throws IOException{
		c.add(a);
		c.add("N");
		b.remove(a);
		b.remove("N");
	}
	//performs the movement of R,L,C
	static void toWest(String a,  ArrayList <String> b, ArrayList<String> c)  throws IOException{
		c.add(a);
		c.add("N");
		b.remove(a);
		b.remove("N");
	}

	//if man is in the certain bank
	static boolean withMan(ArrayList<String> a){
		if(a.contains("N"))
			return true;
		else
			return false;
	}

	//checks of the characters in a bank won't kill each other (meaning, the man is with them)
	static boolean deadCompany(ArrayList <String> a){
		if(a.size() == 2){
			if(a.contains("L") && a.contains("R"))
				return true;
			else if(a.contains("R") && a.contains("L"))
				return true;
			else if(a.contains("C") && a.contains("R"))
				return true;
			else if(a.contains("R") && a.contains("C"))
				return true;
			else
				return false;
		}
		if(a.size() == 3 && a.contains("L") && a.contains("R") && a.contains("C") ){
			return true;
		}
		return false;
	}
}
