
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Anjali
 */
public class Lyics {
    public static void main(String args[]) throws IOException
    {
  File file = new File("C:\\Users\\Anjali\\Desktop\\DBMS API\\URL.txt");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			BufferedWriter bw = new BufferedWriter(fw);
                        bw.newLine();
			bw.write();
                        //bw.write("update songs set +"where songtitle="+"'"+queryTerm+"';");
			bw.close();

    }
}
