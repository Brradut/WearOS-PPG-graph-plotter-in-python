import time
import matplotlib.pyplot as plt
import sys
import signal



line_nr = 0
timestamp_div = 1000000000
channel_div = 1
x = []
y1 = []
y2 = []

frecv = 205

def signal_handler(sig, frame):
    filename = 'default.txt'
    if len(sys.argv) > 1:
        filename = sys.argv[1]
    fl = open(filename, "w+")

    for i in range(0, len(x)):
        fl.write(str(y1[i]) + " " + str(y2[i]) + " " + str(x[i]) + "\n")
    print("Data saved to file: " + filename)
    fl.close()
    sys.exit(0)



signal.signal(signal.SIGINT, signal_handler)

it1, it2, start = input().split(" ")
print("first input")
start = int(start)
x.append(0)
y1.append(float(it1)/channel_div)
y2.append(float(it2)/channel_div)

while True:
    ch1, ch2, t = input().split(" ")
    x.append((int(t) - start)/timestamp_div)
    y1.append(float(ch1)/channel_div)
    y2.append(float(ch2)/channel_div)
    line_nr = line_nr + 1

    if line_nr % frecv == 0:
            plt.clf()
            plt.plot(x, y1)
            plt.plot(x, y2)
            plt.pause(1)
            
    
            
plt.show()
