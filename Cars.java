//@author Brendan Derr, Cody Johnson, Luis Cueto, Bryan Mora
package CARS_System;


import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Cars {
	
		public String ID;
		public String pTitle;
		public String[] genres;
		public Double rating;
		/**
		 * This is a structure to hold all the relevant data from a movie. This is all pulled from the IMDB movies.txt file.
		 * @param i the movies ID number
		 * @param p the movies title
		 * @param g the string array of all the genres the movies is tagged with
		 * @param r the average rating of the movie.
		 */
		public Cars(String i, String p, String[] g, Double r) {
			ID = i;
			pTitle = p;
			genres = g;
			rating = r;
		}
		/**
		 * This function prints out the genres for a particular movie
		 * @param e The string array that holds the genres for a particular movie. 
		 */
		public static void gPrint(String[] e) {
			for(int i=0; i < e.length; i++) {
				if(e[i] != null) {				
				System.out.print(e[i] + " ");
				}
			}
		}
		/**
		 * The sortTable function takes a genre or the overal hashtable of movies, and prints out the top ten from those hashtable.
		 * @param t This is the hashtable is that used to sort the data. 
		 * @param id This is a Hashtable to get a movie name using the movies ID number.
		 */
	    public static void sortTable(Hashtable<String, Double> t, Hashtable<String,String> id){
	        //Transfer as List and sort it
	        ArrayList<Map.Entry<String, Double>> l = new ArrayList(t.entrySet());
	        Collections.sort(l, new Comparator<Map.Entry<String, Double>>(){

	          public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
	             return o2.getValue().compareTo(o1.getValue());
	         }});

	        for(int x = 0; x < 10 && x < l.size(); x++) {
	        	String name = id.get(l.get(x).getKey());
	        	System.out.println("Number " + (x+1) + ": " + name);
	        }
	        System.out.println();
	     }
		
		
		
		
	 public static void main(String[] args) {
		 	//filename is the file the user provides
	        String fileName;
	        //option is a variable used in the interface to figure out what option the user choose
	 		String option;
	 		//This is another variable used in the intergact to figure out what option the user choose based on genre
	 		String genreOption;
	 		
	 		
		  // creating the files into a string array. 		   
		   Scanner inputStream = null;
		   //uses the movie ID and the avg rating from IMDB, is later updated for user input to hold the update rating.
		   Hashtable<String,Double> ratings = new Hashtable<String, Double>();
		   //This is the linked list of movies with all their data stored into structures.
		   LinkedList<Cars> movieList = new LinkedList<Cars>();
		   
		   //takes the Movie Name and it's value is the movie ID number, used for the weight hashtable.
		   Hashtable<String,String> movieID = new Hashtable<String,String>();
		   //This table takes a movies ID number and returns the name of the movie.
		   Hashtable<String,String> idToName = new Hashtable<String,String>();
		   //this table hold the percent values for each genre to be used on the ratings for each movie.
		   Hashtable<String, Double> listG = new Hashtable<String,Double>();
		   //takes the input Movie ID and is keyed for the movies weight
		   Hashtable<String, String[]> stuff = new Hashtable<String, String[]>();

			   try {
				   inputStream = new Scanner(new File("IMDb movies.txt"));
			   }
			   catch(FileNotFoundException e) {
				   System.out.println("error file not found.");
				   System.exit(1);
			   }
		   while(inputStream.hasNextLine()) {
				//preprocessing the file
				String line = inputStream.nextLine();
				String[] words = line.split("\t");
				//testing to make sure the data was put in correctly (aka all information is provided)
				if(words.length < 3) {
					continue;
				}
				String ID= words[0];
				String primTitle = words[1];
				String[] genres = new String[3];
				String g = words[2];
				g = g.replace("\"", "");
				g = g.replace(" ", "");
				genres = g.split(",");		
				String ratingS = words[3];
				Double rat = Double.parseDouble(ratingS);
				Cars movie = new Cars(ID, primTitle, genres, rat);
				ratings.put(ID, rat);
				movieID.put(primTitle, ID);
				idToName.put(ID, primTitle);
				movieList.add(movie);
				stuff.put(primTitle, genres);
		   }
		   inputStream.close();

		   System.out.println("Loading IMDB dataset and processing the data.");
		   System.out.println();
		   //this is where we fill listG(the genre percent table) with all the genres found in the IMDB data set
		   for(int x = 0; x < movieList.size(); x++) {
			   for(int y = 0; y < movieList.get(x).genres.length; y++) {
				   if(movieList.get(x).genres[y]!= null) {
					   if(!listG.containsKey(movieList.get(x).genres[y])) {
						   listG.put(movieList.get(x).genres[y], 0.0);
					   }
				   }
			   }
		   }
  

		   //creating the hashtables to hold the movies by genre
		   Hashtable<String, Double> Adult = new Hashtable<String, Double>();
		   Hashtable<String, Double> History = new Hashtable<String, Double>();
		   Hashtable<String, Double> Reality_TV = new Hashtable<String, Double>();
		   Hashtable<String, Double> Music = new Hashtable<String, Double>();
		   Hashtable<String, Double> Fantasy = new Hashtable<String, Double>();
		   Hashtable<String, Double> Action = new Hashtable<String, Double>();
		   Hashtable<String, Double> Documentary = new Hashtable<String, Double>();
		   Hashtable<String, Double> BioGraphy = new Hashtable<String, Double>();
		   Hashtable<String, Double> Mystery = new Hashtable<String, Double>();
		   Hashtable<String, Double> SciFi = new Hashtable<String, Double>();
		   Hashtable<String, Double> Western = new Hashtable<String, Double>();
		   Hashtable<String, Double> Family = new Hashtable<String, Double>();
		   Hashtable<String, Double> Thriller = new Hashtable<String, Double>();
		   Hashtable<String, Double> Crime = new Hashtable<String, Double>();
		   Hashtable<String, Double> Romance = new Hashtable<String, Double>();
		   Hashtable<String, Double> Adventure = new Hashtable<String, Double>();
		   Hashtable<String, Double> Musical = new Hashtable<String, Double>();
		   Hashtable<String, Double> Horror = new Hashtable<String, Double>();
		   Hashtable<String, Double> News = new Hashtable<String, Double>();
		   Hashtable<String, Double> Film_Noir = new Hashtable<String, Double>();
		   Hashtable<String, Double> Comedy = new Hashtable<String, Double>();
		   Hashtable<String, Double> Sport = new Hashtable<String, Double>();
		   Hashtable<String, Double> Drama = new Hashtable<String, Double>();
		   Hashtable<String, Double> War = new Hashtable<String, Double>();
		   Hashtable<String, Double> Animation = new Hashtable<String, Double>();
		    
		   //sorting the movies into corresponding genre hashtables. It iterates through every movie in the movieList and puts them into
		   //tables based on what genres the movie is tagged in.
		   for(int y = 0; y <movieList.size(); y++) {
			   for(int z = 0; z < movieList.get(y).genres.length; z++) {
				   String g = movieList.get(y).genres[z];
				   //since every movie didn't have three genres.
				   if(g == null) {
					   continue;
				   }				   
				   if(g.equals("Adult")) {
					   Adult.put(movieList.get(y).ID, ratings.get(movieList.get(y).ID));
				   }
				   else if(g.equals("History")) {
					   History.put(movieList.get(y).ID, ratings.get(movieList.get(y).ID));
				   }
				   else if(g.equals("Reality_TV")) {
					  Reality_TV.put(movieList.get(y).ID, ratings.get(movieList.get(y).ID)); 
				   }
				   else if(g.equals("Music")) {
					   Music.put(movieList.get(y).ID, ratings.get(movieList.get(y).ID));
				   }
				   else if(g.equals("Fantasy")) {
					   Fantasy.put(movieList.get(y).ID, ratings.get(movieList.get(y).ID));
				   }
				   else if(g.equals("Action")) {
					   Action.put(movieList.get(y).ID, ratings.get(movieList.get(y).ID));
				   }
				   else if(g.equals("Documentary")) {
					   Documentary.put(movieList.get(y).ID, ratings.get(movieList.get(y).ID));
				   }
				   else if(g.equals("BioGraphy")) {
					   BioGraphy.put(movieList.get(y).ID, ratings.get(movieList.get(y).ID));
				   }
				   else if(g.equals("Mystery")) {
					   Mystery.put(movieList.get(y).ID, ratings.get(movieList.get(y).ID));
				   }
				   else if(g.equals("SciFi")) {
					   SciFi.put(movieList.get(y).ID, ratings.get(movieList.get(y).ID));
				   }
				   else if(g.equals("Western")) {
					   Western.put(movieList.get(y).ID, ratings.get(movieList.get(y).ID));
				   }
				   else if(g.equals("Family")) {
					   Family.put(movieList.get(y).ID, ratings.get(movieList.get(y).ID));
				   }
				   else if(g.equals("Thriller")) {
					   Thriller.put(movieList.get(y).ID, ratings.get(movieList.get(y).ID));
				   }
				   else if(g.equals("Crime")) {
					   Crime.put(movieList.get(y).ID, ratings.get(movieList.get(y).ID));
				   }
				   else if(g.equals("Romance")) {
					   Romance.put(movieList.get(y).ID, ratings.get(movieList.get(y).ID));
				   }
				   else if(g.equals("Adventure")) {
					   Adventure.put(movieList.get(y).ID, ratings.get(movieList.get(y).ID));
				   }
				   else if(g.equals("Musical")) {
					   Musical.put(movieList.get(y).ID, ratings.get(movieList.get(y).ID));
				   }
				   else if(g.equals("Horror")) {
					   Horror.put(movieList.get(y).ID, ratings.get(movieList.get(y).ID));
				   }
				   else if(g.equals("News")) {
					   News.put(movieList.get(y).ID, ratings.get(movieList.get(y).ID));
				   }
				   else if(g.equals("Film_Noir")) {
					   Film_Noir.put(movieList.get(y).ID, ratings.get(movieList.get(y).ID));
				   }
				   else if(g.equals("Comedy")) {
					   Comedy.put(movieList.get(y).ID, ratings.get(movieList.get(y).ID));
				   }
				   else if(g.equals("Sport")) {
					   Sport.put(movieList.get(y).ID, ratings.get(movieList.get(y).ID));
				   }
				   else if(g.equals("Drama")) {
					   Drama.put(movieList.get(y).ID, ratings.get(movieList.get(y).ID));
				   }
				   else if(g.equals("War")) {
					   War.put(movieList.get(y).ID, ratings.get(movieList.get(y).ID));
				   }
				   else if(g.equals("Animation")) {
					   Animation.put(movieList.get(y).ID, ratings.get(movieList.get(y).ID));
				   }
			   }
		   }
		    
		   //all processing stuff is done, ready for input
		   
		   //this is the start of the user interface.
		   Scanner kbInput = new Scanner(System.in);
	 		System.out.println("Hello, please enter in the name of the file with a list of movies that ");
	 		System.out.print("you have watched (Any amount but have them separated by line): ");
	 		fileName = kbInput.nextLine();
	 		System.out.println();	 				 		 	
		  
		   // setting up to read the input file for testing
		   Scanner reader = null;
		   try {
			   reader = new Scanner(new File(fileName));
		   }
		   catch(FileNotFoundException e) {
			   System.out.println("error file not found.");
			   System.exit(1);
		   }
		   double inputM = 0.0;
		   Hashtable<String, String> user = new Hashtable<String,String>();
		   while(reader.hasNextLine()) {
			   String movie = reader.nextLine();
			   //to see if the movie is in our database
			   if(movieID.containsKey(movie)){
				   inputM++;
				   user.put(movie, "");
				   //ID number for the movie.
				   //search the genres for that movie, and increase the number in listG by one for each genres
				   String[] load = stuff.get(movie);
				   for(int t = 0; t< load.length;t++) {
					   String key = load[t];
					   Double count = listG.get(key);
					   listG.put(key, count + 1.0);
				   }
			   }
			   //if the movie name couldn't be found
			   else {
				   System.out.println("Title not found in database " + movie);
			   }
		   }
		   reader.close();
		   
		   //now that we'e read the input file, we need to update the listG rating scale.
	        Enumeration<String> enumeration = listG.keys(); 
	        // iterate using enumeration object
	        //hashtable to store weights by genre
	        while(enumeration.hasMoreElements()) {	 
	            String key = enumeration.nextElement();
	            double val = listG.get(key)/inputM;
	            listG.replace(key, val);
	        }
		   

		   //now that we have our percentages in listG, we can update the ratings for every movie in all the genres and in our master table ratings
	        // the hashtable user is to screen out the movies the user provided in there input file, we don't want to reccomend a movie
	        //they have already seen.
	        Enumeration<String> eNum = ratings.keys();
	        // iterate using enumeration object
	        while(eNum.hasMoreElements()) {	 
	            String key = eNum.nextElement();
	            double val = 0.0;
	            ratings.replace(key, val);
	            if(Adult.containsKey(key) && !user.containsKey(key)) {
	            	double valG = Adult.get(key);
	            	double valX = listG.get("Adult");
	            	valG = valG * valX;
	            	Adult.put(key, valG);
	            	double act = ratings.get(key);
	            	ratings.put(key, (valG + act));
	            }
	            if(History.containsKey(key)&& !user.containsKey(key)) {
	            	double valG = History.get(key);
	            	double valX = listG.get("History");
	            	valG = valG * valX;;
	            	History.put(key, valG);
	            	double act = ratings.get(key);
	            	ratings.put(key, (valG + act));
	            }
	            if(Reality_TV.containsKey(key)&& !user.containsKey(key)) {
	            	double valG = Reality_TV.get(key);
	            	double valX = listG.get("Reality_TV");
	            	valG = valG * valX;
	            	Reality_TV.put(key, valG);
	            	double act = ratings.get(key);
	            	ratings.put(key, (valG + act));
	            }
	            if(Music.containsKey(key)&& !user.containsKey(key)) {
	            	double valG = Music.get(key);
	            	double valX = listG.get("Music");
	            	valG = valG * valX;
	            	Music.put(key, valG);
	            	double act = ratings.get(key);
	            	ratings.put(key, (valG + act));
	            }
	            if(Fantasy.containsKey(key)&& !user.containsKey(key)) {
	            	double valG = Fantasy.get(key);
	            	double valX = listG.get("Fantasy");
	            	valG = valG * valX;
	            	Fantasy.put(key, valG);
	            	double act = ratings.get(key);
	            	ratings.put(key, (valG + act));
	            }
	            if(Action.containsKey(key)&& !user.containsKey(key)) {
	            	double valG = Action.get(key);
	            	double valX = listG.get("Action");
	            	valG = valG * valX;
	            	Action.put(key, valG);
	            	double act = ratings.get(key);
	            	ratings.put(key, (valG + act));
	            }
	            if(Documentary.containsKey(key)&& !user.containsKey(key)) {
	            	double valG = Documentary.get(key);
	            	double valX = listG.get("Documentary");
	            	valG = valG * valX;
	            	Documentary.put(key, valG);
	            	double act = ratings.get(key);
	            	ratings.put(key, (valG + act));
	            }
	            if(BioGraphy.containsKey(key)&& !user.containsKey(key)) {
	            	double valG = BioGraphy.get(key);
	            	double valX = listG.get("Biography");
	            	valG = valG * valX;
	            	BioGraphy.put(key, valG);
	            	double act = ratings.get(key);
	            	ratings.put(key, (valG + act));
	            }
	            if(Mystery.containsKey(key)&& !user.containsKey(key)) {
	            	double valG = Mystery.get(key);
	            	double valX = listG.get("Mystery");
	            	valG = valG * valX;
	            	Mystery.put(key, valG);
	            	double act = ratings.get(key);
	            	ratings.put(key, (valG + act));
	            }
	            if(SciFi.containsKey(key)&& !user.containsKey(key)) {
	            	double valG = SciFi.get(key);
	            	double valX = listG.get("SciFi");
	            	valG = valG * valX;
	            	SciFi.put(key, valG);
	            	double act = ratings.get(key);
	            	ratings.put(key, (valG + act));
	            }
	            if(Western.containsKey(key)&& !user.containsKey(key)) {
	            	double valG = Western.get(key);
	            	double valX = listG.get("Western");
	            	valG = valG * valX;
	            	Western.put(key, valG);
	            	double act = ratings.get(key);
	            	ratings.put(key, (valG + act));
	            }
	            if(Family.containsKey(key)&& !user.containsKey(key)) {
	            	double valG = Family.get(key);
	            	double valX = listG.get("Family");
	            	valG = valG * valX;
	            	Family.put(key, valG);
	            	double act = ratings.get(key);
	            	ratings.put(key, (valG + act));
	            }
	            if(Thriller.containsKey(key)&& !user.containsKey(key)) {
	            	double valG = Thriller.get(key);
	            	double valX = listG.get("Thriller");
	            	valG = valG * valX;
	            	Thriller.put(key, valG);
	            	double act = ratings.get(key);
	            	ratings.put(key, (valG + act));
	            }
	            if(Crime.containsKey(key)&& !user.containsKey(key)) {
	            	double valG = Crime.get(key);
	            	double valX = listG.get("Crime");
	            	valG = valG * valX;
	            	Crime.put(key, valG);
	            	double act = ratings.get(key);
	            	ratings.put(key, (valG + act));
	            }
	            if(Romance.containsKey(key)&& !user.containsKey(key)) {
	            	double valG = Romance.get(key);
	            	double valX = listG.get("Romance");
	            	valG = valG * valX;
	            	Romance.put(key, valG);
	            	double act = ratings.get(key);
	            	ratings.put(key, (valG + act));
	            }
	            if(Adventure.containsKey(key)&& !user.containsKey(key)) {
	            	double valG = Adventure.get(key);
	            	double valX = listG.get("Adventure");
	            	valG = valG * valX;
	            	Adventure.put(key, valG);
	            	double act = ratings.get(key);
	            	ratings.put(key, (valG + act));
	            }
	            if(Musical.containsKey(key)&& !user.containsKey(key)) {
	            	double valG = Musical.get(key);
	            	double valX = listG.get("Musical");
	            	valG = valG * valX;
	            	Musical.put(key, valG);
	            	double act = ratings.get(key);
	            	ratings.put(key, (valG + act));
	            }
	            if(Horror.containsKey(key)&& !user.containsKey(key)) {
	            	double valG = Horror.get(key);
	            	double valX = listG.get("Horror");
	            	valG = valG * valX;
	            	Horror.put(key, valG);
	            	double act = ratings.get(key);
	            	ratings.put(key, (valG + act));
	            }
	            if(News.containsKey(key)&& !user.containsKey(key)) {
	            	double valG = News.get(key);
	            	double valX = listG.get("News");
	            	valG = valG * valX;
	            	News.put(key, valG);
	            	double act = ratings.get(key);
	            	ratings.put(key, (valG + act));
	            }
	            if(Film_Noir.containsKey(key)&& !user.containsKey(key)) {
	            	double valG = Film_Noir.get(key);
	            	double valX = listG.get("Film_Noir");
	            	valG = valG * valX;
	            	Film_Noir.put(key, valG);
	            	double act = ratings.get(key);
	            	ratings.put(key, (valG + act));
	            }
	            if(Comedy.containsKey(key)&& !user.containsKey(key)) {
	            	double valG = Comedy.get(key);
	            	double valX = listG.get("Comedy");
	            	valG = valG * valX;
	            	Comedy.put(key, valG);
	            	double act = ratings.get(key);
	            	ratings.put(key, (valG + act));
	            }
	            if(Sport.containsKey(key)&& !user.containsKey(key)) {
	            	double valG = Sport.get(key);
	            	double valX = listG.get("Sport");
	            	valG = valG * valX;
	            	Sport.put(key, valG);
	            	double act = ratings.get(key);
	            	ratings.put(key, (valG + act));
	            }
	            if(Drama.containsKey(key)&& !user.containsKey(key)) {
	            	double valG = Drama.get(key);
	            	double valX = listG.get("Drama");
	            	valG = valG * valX;
	            	Drama.put(key, valG);
	            	double act = ratings.get(key);
	            	ratings.put(key, (valG + act));
	            }
	            if(War.containsKey(key)&& !user.containsKey(key)) {
	            	double valG = War.get(key);
	            	double valX = listG.get("War");
	            	valG = valG * valX;
	            	War.put(key, valG);
	            	double act = ratings.get(key);
	            	ratings.put(key, (valG + act));
	            }
	            if(Animation.containsKey(key)&& !user.containsKey(key)) {
	            	double valG = Animation.get(key);
	            	double valX = listG.get("Animation");
	            	valG = valG * valX;
	            	Animation.put(key, valG);
	            	double act = ratings.get(key);
	            	ratings.put(key, (valG + act));
	            }
	        }  
	            
	         //Interface Time!
	        //this is the rest of the program which presents the users with the options avaiable to our program.
	         ////////////////////////////////////////////////
	        System.out.println("Processing done.");
	        System.out.println();
	 		System.out.println("What would you like to see from the list of options: ");
	 		System.out.println("1. Top 10 Movies overall for you.");
	 		System.out.println("2. Top 10 Movies based on genre for you.");
	 		System.out.println("0. Exit the program.");
	 		System.out.print("Please enter in a digit based on the list provided: ");
	 		option = kbInput.nextLine();
	 		System.out.println();
	 		
	 		while(!option.equals("0")) {
	 			//prints out the top 10 rated movies based on the entire IMDB data set.
	 			if(option.equals("1")) {	 				
	 				System.out.println("The top 10 movies for you include: ");
	 				sortTable(ratings, idToName);
	 			}
	 			//Prints out a top 10 list of movies based on the genre chosen by the user.
	 			else if(option.equals("2")) {	 				
	 				System.out.println("Please choose which genre you would like to watch based on ");
	 				System.out.println("the number, or the name of the genre from the list provided: ");
	 				System.out.println("1. Adult \t\t 14. Crime");
	 				System.out.println("2. History \t\t 15. Romance");
	 				System.out.println("3. Reality-TV \t\t 16. Adventure");
	 				System.out.println("4. Music \t\t 17. Musical");
	 				System.out.println("5. Fantasy \t\t 18. Horror");
	 				System.out.println("6. Action \t\t 19. News");
	 				System.out.println("7. Documentary \t\t 20. Film-Noir");
	 				System.out.println("8. Biography \t\t 21. Comedy");
	 				System.out.println("9. Mystery \t\t 22. Sport");
	 				System.out.println("10. Sci-Fi \t\t 23. Drama");
	 				System.out.println("11. Western \t\t 24. War");
	 				System.out.println("12. Family \t\t 25. Animation");
	 				System.out.println("13. Thriller");
	 				System.out.println();
	 				System.out.println("Please enter the number or genre from the ");
	 				System.out.print("list provided (case sensitive): ");
	 				genreOption = kbInput.nextLine();
	 				System.out.println();
	 				
	 				if(genreOption.equals("1") || genreOption.equalsIgnoreCase("Adult")) {
	 					sortTable(Adult, idToName);
	 				}
	 				else if(genreOption.equals("2") || genreOption.equalsIgnoreCase("History")) {
	 					sortTable(History, idToName);
	 				}
	 				else if(genreOption.equals("3") || genreOption.equalsIgnoreCase("Reality-TV")) {
	 					sortTable(Reality_TV, idToName);
	 				}
	 				else if(genreOption.equals("4") || genreOption.equalsIgnoreCase("Music")) {
	 					sortTable(Music, idToName);
	 				}
	 				else if(genreOption.equals("5") || genreOption.equalsIgnoreCase("Fantasy")) {
	 					sortTable(Fantasy, idToName);
	 				}
	 				else if(genreOption.equals("6") || genreOption.equalsIgnoreCase("Action")) {
	 					sortTable(Action, idToName);
	 				}
	 				else if(genreOption.equals("7") || genreOption.equalsIgnoreCase("Documentary")) {
	 					sortTable(Documentary, idToName);
	 				}
	 				else if(genreOption.equals("8") || genreOption.equalsIgnoreCase("Biography")) {
	 					sortTable(BioGraphy, idToName);
	 				}
	 				else if(genreOption.equals("9") || genreOption.equalsIgnoreCase("Mystery")) {
	 					sortTable(Mystery, idToName);
	 				}
	 				else if(genreOption.equals("10") || genreOption.equalsIgnoreCase("Sci-Fi")) {
	 					sortTable(SciFi, idToName);
	 				}
	 				else if(genreOption.equals("11") || genreOption.equalsIgnoreCase("Western")) {
	 					sortTable(Western, idToName);
	 				}
	 				else if(genreOption.equals("12") || genreOption.equalsIgnoreCase("Family")) {
	 					sortTable(Family, idToName);
	 				}
	 				else if(genreOption.equals("13") || genreOption.equalsIgnoreCase("Thriller")) {
	 					sortTable(Thriller, idToName);
	 				}
	 				else if(genreOption.equals("14") || genreOption.equalsIgnoreCase("Crime")) {
	 					sortTable(Crime, idToName);
	 				}
	 				else if(genreOption.equals("15") || genreOption.equalsIgnoreCase("Romance")) {
	 					sortTable(Romance, idToName);
	 				}
	 				else if(genreOption.equals("16") || genreOption.equalsIgnoreCase("Adventure")) {
	 					sortTable(Adventure, idToName);
	 				}
	 				else if(genreOption.equals("17") || genreOption.equalsIgnoreCase("Musical")) {
	 					sortTable(Musical, idToName);
	 				}
	 				else if(genreOption.equals("18") || genreOption.equalsIgnoreCase("Horror")) {
	 					sortTable(Horror, idToName);
	 				}
	 				else if(genreOption.equals("19") || genreOption.equalsIgnoreCase("News")) {
	 					sortTable(News, idToName);
	 				}
	 				else if(genreOption.equals("20") || genreOption.equalsIgnoreCase("Film-Noir")) {
	 					sortTable(Film_Noir, idToName);
	 				}
	 				else if(genreOption.equals("21") || genreOption.equalsIgnoreCase("Comedy")) {
	 					sortTable(Comedy, idToName);
	 				}
	 				else if(genreOption.equals("22") || genreOption.equalsIgnoreCase("Sport")) {
	 					sortTable(Sport, idToName);
	 				}
	 				else if(genreOption.equals("23") || genreOption.equalsIgnoreCase("Drama")) {
	 					sortTable(Drama, idToName);
	 				}
	 				else if(genreOption.equals("24") || genreOption.equalsIgnoreCase("War")) {
	 					sortTable(War, idToName);
	 				}
	 				else if(genreOption.equals("25") || genreOption.equalsIgnoreCase("Animation")) {
	 					sortTable(Animation, idToName);
	 				}
	 				else {	 					
	 					System.out.println("That was not an option from the list provided!");	 					
	 				}	 				
	 			}
	 			else {
	 				
	 				System.out.println("That was not an option from the list provided!");
	 				System.out.println();
	 				
	 			}
	 			
	 			System.out.println();
	 			System.out.println("What would you like to do now: ");
	 			System.out.println("1. Top 10 Movies overall for you.");
	 			System.out.println("2. Top 10 Movies based on genre for you.");
	 			System.out.println("0. Exit the program.");
	 			System.out.print("Please enter in a digit based on the list provided: ");
	 			option = kbInput.nextLine();
	 			System.out.println();
	 			
	 		}
	 		
	 		System.out.println("Program exited.");
	 		kbInput.close();  
	               
	 }
}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 

	   
