from random import *
points = [(i,j) for i in range(0,445,55) for j in range(0,280,20)]



for i in range(3):
    shuffle(points)
    file = open("bricks%d.txt" %i,"w")

    for x in range(16):
        file.write(str(points[x][0])+"\n")
        file.write(str(points[x][1])+"\n")

    file.close()
