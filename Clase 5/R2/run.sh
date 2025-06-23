#!/bin/bash

# Script para compilar y ejecutar el proyecto MonitoreoUCI
# Asegúrate de tener reactor-core-3.5.0.jar en el directorio actual

echo "Compilando el proyecto..."
javac -cp reactor-core-3.5.0.jar MonitoreoUCI.java

echo "Ejecutando el proyecto..."
java -cp .:reactor-core-3.5.0.jar MonitoreoUCI
