#include <Arduino.h>

#if defined(ESP32)
  #include <WiFi.h>
  #include <Wire.h>
#elif defined(ESP8266)
  #include <ESP8266WiFi.h>
  #include <Wire.h>
#endif

#include <Firebase_ESP_Client.h>
#include <SoftwareSerial.h>
#include "DFRobotDFPlayerMini.h"
#include "addons/TokenHelper.h"
#include "addons/RTDBHelper.h"

// Firebase Config
#define WIFI_SSID "IOT"
#define WIFI_PASSWORD "12345678"
#define API_KEY "AIzaSyC62MRyXGSzSrHybU2vtOE0lGKTN6mYW1c"
#define DATABASE_URL "https://women-safety-app-58e02-default-rtdb.firebaseio.com/"

// Firebase objects
FirebaseData fbdo;
FirebaseAuth auth;
FirebaseConfig config;
bool signupOK = false;

// DFPlayer Mini
#define DF_TX D4  // TX of DFPlayer
#define DF_RX D3  // RX of DFPlayer
#define BUTTON_PIN D2  // Push button pin

SoftwareSerial mySerial(DF_RX, DF_TX);  // RX, TX
DFRobotDFPlayerMini myDFPlayer;

void setup() {
  pinMode(BUTTON_PIN, INPUT_PULLUP);
  Serial.begin(115200);
  mySerial.begin(9600);

  Serial.println("\nInitializing DFPlayer Mini...");
  for (int i = 0; i < 5; i++) {
    if (myDFPlayer.begin(mySerial)) {
      Serial.println("DFPlayer Mini Initialized!");
      myDFPlayer.volume(30);  // Max volume
      break;
    } else {
      Serial.println("DFPlayer not detected, retrying...");
      delay(1000);
    }
  }

  // Connect to Wi-Fi
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("Connecting to Wi-Fi");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(300);
  }
  Serial.println();
  Serial.print("Connected with IP: ");
  Serial.println(WiFi.localIP());

  // Firebase setup
  config.api_key = API_KEY;
  config.database_url = DATABASE_URL;
  config.token_status_callback = tokenStatusCallback;

  if (Firebase.signUp(&config, &auth, "", "")) {
    Serial.println("Firebase anonymous sign-up successful!");
    signupOK = true;
  } else {
    Serial.printf("Sign-up error: %s\n", config.signer.signupError.message.c_str());
  }

  Firebase.begin(&config, &auth);
  Firebase.reconnectWiFi(true);
}

void loop() {
  if (digitalRead(BUTTON_PIN) == LOW) {
    Serial.println("Button pressed! Playing audio and updating Firebase.");

    // Play audio
    myDFPlayer.play(1);  // Play "0001.mp3"

    // Set Alert = 1 on Firebase
    if (Firebase.RTDB.setString(&fbdo, "/Alert", 1)) {
      Serial.println("Alert set to 1 in Firebase.");
    } else {
      Serial.print("Failed to set Alert = 1. Reason: ");
      Serial.println(fbdo.errorReason());
    }

    delay(1000);  // Wait for 10 seconds

    
  }

  if (myDFPlayer.available()) {
    printDetail(myDFPlayer.readType(), myDFPlayer.read());
  }
}

// Handle DFPlayer status and errors
void printDetail(uint8_t type, int value) {
  switch (type) {
    case TimeOut: Serial.println("DFPlayer Timeout!"); break;
    case DFPlayerCardInserted: Serial.println("SD Card Inserted!"); break;
    case DFPlayerCardRemoved: Serial.println("SD Card Removed!"); break;
    case DFPlayerCardOnline: Serial.println("SD Card Online!"); break;
    case DFPlayerPlayFinished:
      Serial.print("Track "); Serial.print(value); Serial.println(" Finished!"); break;
    case DFPlayerError:
      Serial.print("DFPlayer Error: ");
      switch (value) {
        case Busy: Serial.println("SD Card not found!"); break;
        case FileMismatch: Serial.println("Cannot Find File!"); break;
        default: Serial.println("Unknown Error!"); break;
      }
      break;
  }
}