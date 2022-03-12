1: Windows setup
	-run WINDOWS.exe (allow through firewall if needed)
2: Linux setup
	-sudo ./LINUX.sh


To use:
	Open XLaunch, leave everything default except "Disable access control". Make sure that box is checked.

	Connect device to adb
	./adb.exe connect ip
	./start.sh [device_ip]
	Run the project
	CTRL+C in console to save data to file

	Explore graph
	python3 build-graph.py [filename]
	CTRL+C in console to quit




WINDOWS.exe - XLaunch installer. Matplotlib needs this to show the graph if running in WSL.
LINUX.sh - installs python and dependencies. Also sets up environment variables for XLaunch 
adb.exe - adb executable for windows.
