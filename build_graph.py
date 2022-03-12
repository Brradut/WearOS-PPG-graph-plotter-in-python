import matplotlib.pyplot as plt
import sys

filename = "default.txt"

if len(sys.argv) > 1:
    filename = sys.argv[1]

fl = open(filename, 'r')

x = []
y1 = []
y2 = []
for line in fl:
    row = line.split(" ")
    y1.append(float(row[0]))
    y2.append(float(row[1]))        
    x.append(float(row[2]))

fl.close()
frecv = 205
nr_sec = 233
offset = 0



print("time to make plots")

plt.plot(x, y1)
plt.plot(x, y2)

plt.show(block = False)

while True:
    offset, nr_sec = input("Offset, nr_sec: ").split(" ")
    offset = int(offset)
    nr_sec = int(nr_sec)
    plt.clf()
    st = frecv * offset
    dr = st + frecv * nr_sec
    plt.plot(x[st:dr], y1[st:dr])
    plt.plot(x[st:dr], y2[st:dr])
    plt.show(block = False)
