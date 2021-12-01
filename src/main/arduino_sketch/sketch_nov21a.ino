
#include <Arduino_JSON.h>
#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <WiFiClient.h>
#include <SoftwareSerial.h>


//SoftwareSerial mySerial(10, 11); // RX, TX

const char* ssid = "MTS_Router_096573";
const char* password = "kaAUCXPe3q";

//Your Domain name with URL path or IP address with path
//const char* serverName = "http://192.168.1.2:8090/api/v1.0/";
const String serverName = "http://192.168.1.3:8090/api/v1.0/";

const String tempApiPath = serverName + "temp";
const String humidityApiPath = serverName + "humidity";

// the following variables are unsigned longs because the time, measured in
// milliseconds, will quickly become a bigger number than can be stored in an int.
unsigned long lastTime = 0;
// Timer set to 10 minutes (600000)
//unsigned long timerDelay = 600000;
// Set timer to 5 seconds (5000)
unsigned long timerDelay = 15000;

String sensorReadings;
float sensorReadingsArr[3];

int cipherKey = 10;

struct Str {
  float t;
  float h;
};

Str buf;

void setup() {
  Serial.begin(115200);

  WiFi.begin(ssid, password);
  Serial.println("Connecting");
  while(WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.print("Connected to WiFi network with IP Address: ");
  Serial.println(WiFi.localIP());
 
  Serial.println("Timer set to 5 seconds (timerDelay variable), it will take 5 seconds before publishing the first reading.");
  
  //mySerial.begin(4000);
}

void loop() {
  // Send an HTTP POST request depending on timerDelay
  if ((millis() - lastTime) > timerDelay) {
    //Check WiFi connection status
    if(WiFi.status()== WL_CONNECTED){
              
      sendDataToServer(tempApiPath, encrypt(getTemp(), cipherKey));
      delay(500);
      sendDataToServer(humidityApiPath, encrypt(getHumidity(), cipherKey));

      /*
      if(mySerial.available()) {
          sendDataToServer(tempApiPath, String(120));
          mySerial.readBytes((byte*)&buf, sizeof(buf));
          sendDataToServer(tempApiPath, String(buf.t, 3));
          sendDataToServer(humidityApiPath, String(buf.h, 3));  
      }
      */
    }
    else {
      Serial.println("WiFi Disconnected");
    }
    lastTime = millis();
  }
}


void sendDataToServer(String apiPath, String sensorValue){
      httpPOSTRequest(apiPath, sensorValue);
}

//функция шифрования данных
String encrypt(String data, int key) {
    Serial.println("Data: ");
    Serial.println(data);
    for(int i = 0; i < data.length(); i++) {
        data.setCharAt(i, data.charAt(i) + key);
    }
    Serial.println("Encrypted data: ");
    Serial.println(data);
    return data;
}

String getTemp(){
  //код, получающий показания с сенсора температуры и переводящий его в строку
  int tempRand = random(18, 27);
  return String(tempRand);  
}

String getHumidity(){
  //код, получающий показания с сенсора влажности и переводящий его в строку
  int humidityRand = random(60, 80);
  return String(humidityRand);  
}



void httpPOSTRequest(String apiPath, String value){
    WiFiClient client;
    HTTPClient http;
      
    // Your IP address with path or Domain name with URL path 
    http.begin(client, apiPath);
    //http.addHeader("Content-Type", "text/plain");
    // Send HTTP POST request
    int httpResponseCode = http.POST(value);
      
    Serial.print(http.getString()); 

    if (httpResponseCode>0) {
      Serial.print("HTTP Response code: ");
      Serial.println(httpResponseCode);
    }
    else {
      Serial.print("Error code: ");
      Serial.println(httpResponseCode);
    }
    // Free resources
    http.end();
}
