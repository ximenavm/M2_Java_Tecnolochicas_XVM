
# MeridianPrime - Simulación Reactiva de Sistemas Críticos en una Ciudad Inteligente

Este proyecto utiliza **Project Reactor** para simular en tiempo real la gestión reactiva y no bloqueante de cinco sistemas críticos en **Meridian Prime** (ciudad ficticia inspirada en Horizon Zero Dawn).

## 🎯 Sistemas Simulados
1. 🚗 **Sensores de tráfico** – Detectan congestión en avenidas principales (>70% congestión).
2. 🌫️ **Contaminación del aire** – Alertan si PM2.5 supera los 50 µg/m³.
3. 🚑 **Accidentes viales** – Prioridad solo para emergencias (prioridad Alta).
4. 🚝 **Trenes maglev** – Alertan si retraso es mayor a 5 minutos.
5. 🚦 **Semáforos inteligentes** – Alertan si hay rojo consecutivo ≥ 3 veces.

## 🔄 Flujo Reactivo
Cada sistema es un `Flux` independiente que:
- Genera datos simulados en intervalos específicos (400-800 ms).
- Filtra eventos que cumplen el criterio crítico.
- Emite una alerta por consola para cada evento relevante.

Se utiliza `onBackpressureBuffer()` para simular acumulación controlada en el flujo de tráfico.

## 🔔 Alertas Globales
Si en un lapso de 1 segundo se detectan 3 o más eventos críticos en simultáneo, se emite una alerta global:
```
🚨 Alerta global: Múltiples eventos críticos detectados en Meridian Prime
```

## 🏃‍♂️ Ejecución
Compila y ejecuta el programa:
```bash
javac -cp reactor-core-3.5.0.jar MeridianPrime.java
java -cp .:reactor-core-3.5.0.jar MeridianPrime
```

El programa permanece en ejecución durante unos segundos para permitir la visualización de eventos simulados.

## 📂 Estructura
```
MeridianPrime.java   # Código principal
README.md           # Documentación del proyecto
```

¡Disfruta explorando este flujo reactivo! 🚀
