#include "net_autonav_Subsystems_System_RobotCommunicator.h"
#include "FRCLibDS/include/LibDS.h"
#include <stdio.h>
#include <iostream>
using namespace std;

list<int[]
JNIEXPORT void JNICALL Java_net_autonav_Subsystems_System_RobotCommunicator_sendJoystickMovement
  (JNIEnv *env, jclass cls, jchar direction, jfloat speed) {
    std::cout << "Sending joystick movement" << std::endl;
    
}

JNIEXPORT void JNICALL Java_net_autonav_Subsystems_System_RobotCommunicator_subscribeRobotEvents
  (JNIEnv *env, jclass cls, bool value) {
    if (value) {
      DS_Event event;
      while (DS_PollEvent (&event)) {
          switch (event.type) {
            case DS_ROBOT_ENABLED_CHANGED:
              printf("Robot enabled changed\n");
              break;
          }
      }
    }
} //TODO: Fix this

JNIEXPORT void JNICALL Java_net_autonav_Subsystems_System_RobotCommunicator_setRobotController
  (JNIEnv *env, jclass cls, string controller) {
    if (controller == "AUTO") {
      DS_SetControlMode(DS_ControlMode::DS_CONTROL_AUTONOMOUS);
    } else {
      DS_SetControlMode(DS_ControlMode::DS_CONTROL_TELEOPERATED);
    }
}

int main() {
  DS_Init();

  DS_ConfigureProtocol(DS_GetProtocolFRC_2020());

  DS_SetTeamNumber(293);
  return 0;
}

// LINUX COMPILE COMMAND: 
// g++ -c -fPIC -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux Communication.cpp -o Communication.o

// LINUX LINK COMMAND:
// g++ -shared -o libnative.so Communication.o

// WINDOWS COMPILE COMMAND:
// g++ -c -I%JAVA_HOME%\include -I%JAVA_HOME%\include\win32 Comminication.cpp -o Comunication.o

// g++ -shared -o native.dll Communication.o -Wl,--add-stdcall-alias