
# MonitoreoUCI - Sistema Reactivo para Unidad de Cuidados Intensivos

Este proyecto simula un sistema reactivo y no bloqueante que monitorea los signos vitales en tiempo real de 3 pacientes en una UCI, aplicando backpressure para procesar eventos críticos a un ritmo controlado.

## 🧠 Contexto
Los pacientes emiten señales con sus signos vitales cada 300 ms:
- Frecuencia cardíaca (FC)
- Presión arterial sistólica/diastólica (PA)
- Saturación de oxígeno (SpO2)

## 🎯 Condiciones críticas
- FC < 50 bpm o FC > 120 bpm
- PA sistólica > 140 mmHg o diastólica > 90 mmHg
- PA sistólica < 90 mmHg o diastólica < 60 mmHg
- SpO2 < 90%

## ⚙️ Comportamiento
- Cada paciente es monitoreado en un `Flux` independiente.
- Los eventos se filtran para emitir solo signos vitales anómalos.
- `Flux.merge()` combina las señales de los 3 pacientes en un solo flujo.
- `delayElements()` introduce backpressure para procesar los eventos a un ritmo de 1 segundo.
- Las alertas se imprimen en consola con formato:
```
⚠️ Paciente 1 - FC crítica: 130 bpm
⚠️ Paciente 2 - SpO2 baja: 85%
⚠️ Paciente 3 - PA crítica: 150/95 mmHg
```

## 🏃‍♂️ Ejecución
Compila y ejecuta el proyecto con Project Reactor en tu classpath:
```
javac -cp reactor-core-3.5.0.jar MonitoreoUCI.java
java -cp .:reactor-core-3.5.0.jar MonitoreoUCI
```
La simulación se mantendrá viva por unos segundos para que se generen suficientes eventos.

## 🎉 Resultado esperado
Se imprimen eventos críticos de cada paciente con un control del ritmo de procesamiento para evitar saturar el sistema, tal como lo haría un sistema real en una UCI.

