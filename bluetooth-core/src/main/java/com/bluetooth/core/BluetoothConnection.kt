package com.bluetooth.core

interface BluetoothConnection {
    fun send(data: ByteArray)
    fun receive(): ByteArray
    fun close()
}