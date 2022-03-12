# WearOS-PPG-graph-plotter-in-python

This project was born due to a need of seeing PPG sensor data in real-time, while also being able to save the data for later analysis. Copying the database from the watch every time was a pain, so I came up with this solution.


On the watch side, the MeasureData app runs. All this app does is log the sensor data to Logcat, while also saving it in a local database. The watch is wirelessly connected to adb, from which we get the data in the following format: channel1_value channel2_value timestamp_in_nanoseconds. The graph is then plotted with matplotlib, and updated in real-time. Killing the process with Ctrl + C stops the program, and saves the data to a file.


The project assumes the usage of WSL, in which everything is run (only tested on Debian 10). XLaunch is used for matplotlib to de able to show the graph. 



Files used:

MeasureData - the project containing the WearOS app. The watch interface shows nothing, you need to check logcat to see if it's working.

adb.exe - the adb executable. 

AdbWinApi.dll & AdbWinUsbApi.dll - libraries for adb to work on Windows.

build_graph.py - used to visualize the graph from a file, or from default.txt if no file is given. Permits choosing chunks of time by giving an offset and a duration.

INSTRUCTIONS.md - instructions regarding installation.

LINUX.sh - setup script for WSL.

live-data.py - builds the graph in real-time using STDIN input. When receiving SIGINT saves the data to the given file, or to default.txt if no file is given. Is used by start_graph.sh.

requirements.txt - python requirements, made with pip freeze. Used by LINUX.sh

start-graph.sh - clears logcat data, and then pipes the PPG data coming from the watch to live-data.py, which then plots the graph in real time. If multiple devices are connected to adb, you can give the device IP as argument.

WINDOWS.exe - XLaunch installer.



The app has only been tested on the TicWatch Pro, which is known to have this sensor id, and this data format. Different devices may, and will sometimes, have different sensor ids, and different data formats. To check the list of sensors, start the app, and filter logcat logs by "Device List". Search for a sensor containing PPG, and change the value of the constant PPG_SENSOR to the correct one. To check the data format, comment line 53 and uncomment line 55. For the python script to work, it needs to receive data in the aformentioned format.
