package com.bluetooth.core

interface BluetoothAdapter {
    fun isEnabled(): Boolean
    fun getDevices(): List<BluetoothDevice>
}