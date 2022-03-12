#!/bin/bash


if [ $# -eq '0' ]
then

	./adb.exe  logcat -b all -c
	./adb.exe  logcat -v raw -s PPG | grep [0-9].* | python3 live-data.py

else

	./adb.exe -s $1 logcat -b all -c
	./adb.exe -s $1 logcat -v raw -s PPG | grep [0-9].* | python3 live-data.py

fi



