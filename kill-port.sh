#!/bin/bash

PORT=$1

# Try lsof first
if command -v lsof > /dev/null; then
    PID=$(lsof -t -i:$PORT)
# Try netstat if available
elif command -v netstat > /dev/null; then
    PID=$(netstat -tulpn | grep :$PORT | awk '{print $7}' | cut -d'/' -f1)
# Try ss as last resort (comes with iproute2, usually installed by default)
elif command -v ss > /dev/null; then
    PID=$(ss -lptn "sport = :$PORT" | grep -oP '(?<=pid=)\d+')
else
    echo "No supported tools found (lsof, netstat, or ss). Please install one of them."
    exit 1
fi

if [ ! -z "$PID" ]; then
    echo "Found process $PID using port $PORT"
    kill -9 $PID 2>/dev/null && echo "Process killed" || echo "Failed to kill process"
else
    echo "No process found using port $PORT"
fi