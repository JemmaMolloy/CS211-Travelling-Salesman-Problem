import java.util.*;
import java.io.*;

public class TSP {

	public static void main(String[] args)
	{
		
		Brain path = new Brain();
		
		double lat[] = new double[1001];
		double lon[] = new double[1001];
		FileIO reader = new FileIO();
        	String[] inputsLat = reader.load("C://Users//JM//Desktop//Lat.txt");    //Reading the File as a String array
        	String[] inputsLon = reader.load("C://Users//JM//Desktop//Lon.txt");    //Reading the File as a String array
		for(int i=0;i<=1000;i++)
		{ 
			lat[i]=Double.parseDouble(inputsLat[i]);
			lon[i]=Double.parseDouble(inputsLon[i]);
		}
		ArrayList<Integer> cities = new ArrayList<Integer>();
		double dist[][] = new double[1001][1001]; 
		for(int i =0; i<=1000;i++)
		{
			for(int j=0;j<=1000;j++)
			{
				dist[i][j]=1000000;
			}
			cities.add(i);
		}
		for(int i=0;i<=1000;i++)
		{
			for(int j=0;j<=1000;j++) 
			{
				double distance = distance(lat[i],lon[i],lat[j],lon[j]);
				if(distance>=100)
				{
					dist[i][j]=distance;
				}
			}
		}
		int start=0;
		while(cities.size()>0)
		{
			double min=10000000;
			int current=0;
			if(cities.size()==1001)
			{
				current=688;
			}
			else if(cities.size()==500)
			{
				current=577;
			}
			else 
			{	
				for(int i=0;i<1001;i++)
				{
					
					if(min>dist[start][i]&&cities.contains(i))
					{
						current=i;
						min=dist[start][i];
					}
				}
			}
			cities.remove(new Integer(start));
			path.current.add(start);
			start=current;
		}
		path.setDistance(dist);        
		path.Searching();
		
		 String newPath = "";
         for (int i = 0; i < path.getCitiesOrder().size()-1; i++)
         {
             newPath += path.getCitiesOrder().get(i)+",";
				
         }
         
         newPath += path.getCitiesOrder().get(path.getCitiesOrder().size() - 1);
         
         System.out.println("Best tour: " + newPath+",0");
         System.out.println("The distance is: " + (int)path.shortestDist);
         System.out.println("The time is: " + (int)path.shortestTime);
	}

	public static double distance(double M1, double M2, double W1, double W2)
    {
        if(M1==W1&&M2==W2)//if the same no distance
        {
            return 0;
        }
        //to radians
        double lat1 = M1/(180/3.141);
        double lat2 = W1/(180/3.141);
        double lon1 = M2/(180/3.141);
        double lon2 = W2/(180/3.141);

        double dist1= Math.sin(lat1)*Math.sin(lat2);
        double dist2= Math.cos(lat1)*Math.cos(lat2);
        double distance=Math.acos(dist1+dist2*Math.cos(lon1-lon2))*6371;

        return distance;
    }
	
}
class Brain {
	
	public List<Integer> current = new ArrayList<>();
	public List<Integer> next = new ArrayList<>();
	public double[][] distances;
	public Random random = new Random();
	public double shortestDist = 1000000000;
	public double shortestTime = 0;
	public double hours=0;
	
	
	public double getShortestDist()
    {
      return shortestDist;
    }
    public void setDistance(double[][] dist)
    {
        distances=dist;
        
    }
    public void setShortestDistance(double value) {
    	this.shortestDist = value;
    }
    
    public List<Integer> getCitiesOrder(){
    	return current;
    }
    
    public List<Integer> setCitiesOrder(List<Integer> value){
    	return current = value;
    }
    public double totalDistance(List<Integer> cities)
    {
        double distance = 0;
        int last=0;
        hours=0;
        double mps = 800000/3600;
          
        for (int i = 0; i < cities.size() - 1; i++)
        {
        	if(distances[cities.get(i)][cities.get(i + 1)]<100)
        	{
        		return 10000000;
        	}
            distance += distances[cities.get(i)][cities.get(i + 1)];
            hours=hours+distances[cities.get(i)][cities.get(i + 1)]/mps/3.6+0.5;
            last=cities.get(i + 1);
        }
        distance +=distances[last][0];
        hours=hours+distances[last][0]/mps/3.6+0.5;
        return distance;
    }
    
    public List<Integer> nextPath(List<Integer> cities)
    {
        List<Integer> cities1 = new ArrayList<>();

        for (int i = 0; i < cities.size(); i++) {
            cities1.add(cities.get(i));
        }
        // rearrange two random cities
        int RandomCity1 = random.nextInt(1000)+1;
        int RandomCity2 = random.nextInt(1000)+1;
        
        int temp = cities1.get(RandomCity1);
        cities1.set(RandomCity1,cities1.get(RandomCity2));
        cities1.set(RandomCity2,temp);
        
        return cities1;
    

    }
    public void Searching()
    {
        double newDistance = 0;

        double distance = totalDistance(current);
      
        while (true&&distance>100000)//what I believe is the shortest distance possible
        {
            next = nextPath(current);

            newDistance = totalDistance(next);

            if ((newDistance < distance))
            {
                for (int i = 0; i < next.size(); i++) 
                {
                	current.set(i,next.get(i));
                }

                distance = newDistance;
                if(distance<415000)//current shortest distance - print out path if new shortest found as might not reach optimal distance
                {
                	for (int i = 0; i < next.size(); i++) 
                	{
                		System.out.print(current.get(i)+",");
                    }
                	System.out.println("0");
                }
                
                System.out.println("Distance:"+distance);
                System.out.println("Time:"+hours);
                shortestDist = distance;
                shortestTime = hours;
            }
        } 
    }
}

class FileIO
{

  public String[] load(String file) 
  {
	    File aFile = new File(file);     
	    StringBuffer contents = new StringBuffer();
	    BufferedReader input = null;
	    try {
	      input = new BufferedReader( new FileReader(aFile) );
	      String line = null; 
	      int i = 0;
	      while (( line = input.readLine()) != null){
	        contents.append(line);
	        i++;
	        contents.append(System.getProperty("line.separator"));
	      }
	    }catch (FileNotFoundException ex) {
	      System.out.println("Can't find the file - are you sure the file is in this location: "+file);
	      ex.printStackTrace();
	    }catch (IOException ex){
	      System.out.println("Input output exception while processing file");
	      ex.printStackTrace();
	    }
	    finally {
	      try {
	        if (input!= null) {
	          input.close();
	        }
	      }
	      catch (IOException ex) {
	        System.out.println("Input output exception while processing file");
	        ex.printStackTrace();
	      }
	    }
	    String[] array = contents.toString().split("\n");
	    for(String s: array){
	        s.trim();
	    }
	    return array;
  }
  public void save(String file, String[] array) throws FileNotFoundException, IOException {

	    File aFile = new File(file); 
	    Writer output = null;
	    try {
	      output = new BufferedWriter( new FileWriter(aFile) );
	      for(int i=0;i<array.length;i++){
	        output.write( array[i] );
	        output.write(System.getProperty("line.separator"));
	      }
	    }
	    finally {
	      if (output != null) output.close();
	    }
	  }
}