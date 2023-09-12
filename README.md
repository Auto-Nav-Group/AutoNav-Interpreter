# AutoNav-Interpreter

## Incomming directions from server format (JSON)

```json
{
  "action": {},
  "location": {},
  "priority": {}
}
```
Example STOP JSON Object:

```json
{
  "action": {STOP},
  "location": {0, 0, 0},
  "priority": {HIGHEST}
}
```

That json block tells the interpreter to stop sending odometry data, and stop taking in any commands exept a START command. All robot action will be overriden by user input. 

## Outgoing odometry data to the server

```json
{
  "currentloc": {},
  "timestamp": {},
  "pitch": {}
}
```

Breaking it down:
- currentloc:
  - Relative to 0, 0 (QIV)
  - Format of (X, Z) (No 3D needed)
- timestamp:
  - Formatted in UNIX timestamp
- pitch:
  - In degrees, will not exceed 10 (automatic override)

## Message JSON objects

The robot is cabable of sending messages, containing general information, or sending warning messages. Below are some examples of warning messages and general information sent from the robot to the server.

```json
{
  "level": {},
  "message": {}
}
```
That is the basic JSON object format of a message. Applicalble levels for the messages are: 

- INFO
- WARNING
- DEBUG
- STAT

INFO messages would just be general information sent from the robot. Examples could include:
- "All system operational"
- "Enabling AutoNav"
- "Disabling AutoNav"

WARNING messages should be dispalyed above everything else. Examples could inlcude:
- "Pitch exceeded 10°. Enabling operator override"
- "Not receiving movement commands from server. Enabling operator override"

DEBUG messages can be logged to a debug console or file. These are used primarily in development. Examples could include:
- "Movement information recieved. (Information)"

STAT messages are just general statistics the robot is sending to the user. Examples could include:
- Current speed
- Current pitch
- Current location
