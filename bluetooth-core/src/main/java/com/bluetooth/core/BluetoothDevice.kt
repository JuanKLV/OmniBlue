package com.bluetooth.core

interface BluetoothDevice {
    val name: String
    val address: String
    fun connect(): BluetoothConnection
}