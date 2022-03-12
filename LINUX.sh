#!/bin/bash

apt-get install python3
python3 -m pip install -r requirements.txt
export DISPLAY=localhost:0.0
echo export DISPLAY=localhost:0.0 >> ~/.bashrc

