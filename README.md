# 🌡️ Taller IoT – Del ESP32 al Dashboard en Tiempo Real

Este proyecto muestra cómo conectar un **ESP32 con sensor DHT11** a un backend en **Spring Boot**, almacenar lecturas en **MongoDB** y visualizarlas en **Grafana** en tiempo real.  

---

## 📌 Objetivo
Aprender a construir un flujo IoT completo:
1. 📡 Conectar un ESP32 y leer temperatura/humedad.  
2. 📤 Enviar datos en JSON a un backend.  
3. 🗄️ Guardar lecturas en una base de datos MongoDB.  
4. 📊 Visualizar la información en tiempo real con Grafana.  

---

## 🛠️ Tecnologías utilizadas
- **Hardware:** ESP32 + DHT11  
- **Programación IoT:** Arduino IDE (C++)  
- **Backend:** Java 17 + Spring Boot  
- **Base de datos:** MongoDB  
- **Visualización:** Grafana  
- **Contenedores:** Docker & Docker Compose  

---

## 📂 Estructura del proyecto
```bash
Temperature-esp32/
├── src/main/java/org/casesp32/temperatureesp32
│   ├── controller       # Endpoints REST (API)
│   ├── service          # Lógica de negocio
│   ├── domain           # Modelo SensorReading
│   ├── repository       # Acceso a MongoDB
│   ├── dto              # Requests / Responses
│   └── realtime         # Streaming SSE
├── docker-compose.yml   # Orquestación (backend + mongo + grafana)
├── Dockerfile           # Imagen backend
├── Dockerfile.grafana   # Imagen grafana con plugin MongoDB
└── README.md
```
---

## 🚀 Cómo ejecutar el backend
Clonar el repositorio:
```bash
git clone <url-del-repo>
cd Temperature-esp32

```
Levantar con Docker Compose:
```bash
docker-compose up --build
```
Servicios disponibles:
```bash
POST /api/readings → recibe lecturas de los sensores.

GET /api/readings/latest?deviceId=ESP32-1&limit=50 → devuelve las últimas lecturas.

GET /api/readings/stream → stream en vivo con Server-Sent
```
