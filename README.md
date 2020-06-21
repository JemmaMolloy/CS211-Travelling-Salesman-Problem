# CS211-Travelling-Salesman-Problem

The aim of the travelling salesman problem is starting at a fixed position “a” in this case “0”, to visit every city in the list (of 1000 other cities) and return to “0” preferably only visiting every city once. 

The solution to this problem is a minimum cost Hamiltonian cycle.

When researching the problem I came across many algorithms including but not limited to:

Brute Force, Genetic, HeldKarp, Chrisofides, Simulated Annealing (*), Branch and Bound, Using Minimal Spanning Trees , Dijkstra etc.

I wrote algorithms to try and implement all of the above and others but most proved insufficient or too slow from a span of 1001 cities.

My solution to the project is based mostly on the Nearest Neighbor algorithm.

(*) The “Brain” class is based on ideas I saw in the Simulated Annealing algorithm.

I read in the latitude and longitude coordinates by putting them each into their own text file and reading it into two separate arrays using the “FileIO” class from moodle - given by lecturer. 

The algorithm used to find the distance is based on the Haversine formula and is what I used in CS211 lab 9. All the distances were initially set to 1000000 (representing infinity), and then replaced using this method with the actually distances between all cities (or nodes). If the distance between two cities was not greater or equal to 100km it was kept at “infinity”.

I used the Nearest Neighbor part of the project to find a solution string of low mileage – 526370Km , 1159 hours (originally 589075Km , 1239 hours without the fixed points).

Starting with an ArrayList of size 1001 and start of 0 it went through each city position to find the nearest city that had not already been visited. After a city was visited it was removed from the cities list. Two points were fixed, as seen in this loop, for the purpose of trying to find the shortest route and visiting all cities only once (as this affected the starting distance).

This part of the project was quite simple and while working on it, it was run multiple times to find a sufficient solution string to start.

I did all this in the main “TSP” class to show that I completed this project in pieces.

After this is the “Brain” class where I have multiple methods based on the Simulated Annealing algorithm.

The idea behind the “Brain” of the project was to start with the solution string from the main “TSP” class and from that create a new solution string or “path” using ArrayLists and find its distance. If this distance was smaller than the previous, make this the new smallest distance and so on.

While at this part in the Simulated Annealing algorithm the while loop would continue until the temperature had cooled down, I removed this and had the loop running indefinitely or until the best solution possible was found. I kept track of the current best distance and if that was exceeded I printed the path and time.

This loop could take from 10mins to half an hour or longer to find the current best solution because of the randomness used in swapping two cities to make a new “path”. Randomness was also used to try and ensure my final path did not match another student’s path.

As this is a NP problem there is no optimal solution and I could leave this program running all day and it might not reach a shorter distance than I have already found. But then again I could be wrong as I know there are more optimal algorithms out there to try and solve this problem.

I chose to complete this project in Java as it is my preferred language, though Python would be a close second, and I had already completed lab 9 in Java from which I used to find the distance.

I originally implemented this project with graphs but I found (for the first part anyway) it was more efficient to just used an ArrayList as I had already used the latitude, longitude and distance arrays, it was just wasting space.

For the solution string submitted it took around 15 minutes to find, unfortunately I had no timer set up but according to the time started and terminated in eclipse only 15:47 minutes had passed.

That being said when ran again and running for 30 minutes no better nor equivalent solution had been found and I ran the project again.

My best path found took 414258 Km, 1018 Hours.

The implementation of the “Brain” class cut my time by 141 hours when compared to the initial result from the Nearest Neighbor algorithm.

Overall my implementation of the TSP is quite basic using only arrays and AarrayLists but it gives a valid solution. The use of the multiple methods in the “Brain” class may slowed it down but I believe it is effective given it is an NP problem.


