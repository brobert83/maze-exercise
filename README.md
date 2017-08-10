### The spec
Example input

	OOOOOOOOOO
	O    O   O
	O OO O O O
	O  O O O O
	O OO   O  
	O OOOOOOOO
	O        O
	OOOOOOOOOO
	
Starting point: (x=3, y=1)
 	
 	OOOOOOOOOO
	O    O   O
	O OO O O O
	O• O O O O
	O OO   O  
	O OOOOOOOO
	O        O
	OOOOOOOOOO
	
Desired output:
	
	OOOOOOOOOO
	O••••O•••O
	O•OO•O•O•O
	O• O•O•O•O
	O OO•••O••
	O OOOOOOOO
	O        O
	OOOOOOOOOO
	
### Notes:
* The testing part makes use of AssertJ, Mockito, SpringBoot and Unirest(to simulate a black-box style)
* I have started the work top-down, which is why two hours passed just by wiring everything up 
* I have done many commits to show how I usually do things and further underline the top-down approach, so TDD basically
* The coverage is not great at the integration level, there should be more mazes added but it was too much trouble to integrate a maze generator or something
* There are a few more things I would like to improve (maybe tomorrow): 
    * the sub-routines moved to a different class
    * better package design
    * more coverage
    
 
   	