import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.*;


public class FirstTaskUrl{
  public static void main(String[] args) throws Exception {

    BufferedReader emailAddress = new BufferedReader(new InputStreamReader(System.in));
    String idEmail = emailAddress.readLine();

   	String webPage = "http://www.ecs.soton.ac.uk/people/";

    String urlNeeded = webPage + idEmail;

   	URL newUrl = new URL( newUrl );

   	BufferedReader readUrlForTheWin = new BufferedReader(new InputStreamReader(sp.openStream()));
   	String lineByLine;
   	String whatIAmLookingFor = "uos-page-title uos-main-title uos-page-title-compressed";
   	int index = 0;
   	while( (lineByLine = readUrlForTheWin.readLine() ) != null && index < 88 ){
          index ++;
  	}


    int closeEnough = lineByLine.indexOf(whatIAmLookingFor) + whatIAmLookingFor.length() + 18;

    String theFinalName =lineByLine.substring(closeEnough);

    int finallyTheEnd = theFinalName.indexOf("<");

    System.out.println( theFinalName.substring(0, finallyTheEnd ));
   
    in.close();
  }
}
