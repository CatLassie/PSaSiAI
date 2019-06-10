example how to run the jar:

java -jar vertexcover.jar 005 random relaxed 0.95 0.01 14

"005" is the instance number (in the instance file number)
"random" is the construction heuristic type (options: random / greedy / worst)
"relaxed" is the neighbourhood type (options: strict / relaxed)
"0.95" (OPTIONAL) is the cooling rate (value between 0 and 1, default is 0.95)
"0.01" (OPTIONAL) is the minimum temperature (stopping condition, default is 0.01)
"14" (OPTIONAL) is the equilibrium coefficient (multiplied with vertex number gives the number of iterations before a cooling occurs,
default is square root of vertex number rounded down)

instances have to be placed in an "instances" folder which needs to be on the same level as the jar
a "solutions" folder has to be created, which needs to be on the same level as the jar