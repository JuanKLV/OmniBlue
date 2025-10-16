package com.bluetooth.windows


fun main() {
    val adapter = WindowsBluetoothAdapter()
    val devices = adapter.getDevices()

    if (devices.isEmpty()) {
        println("No se encontraron dispositivos")
    } else {
        devices.forEach {
            println("Dispositivos encontrados ${it.name} y ${it.address}")
        }
    }
}
