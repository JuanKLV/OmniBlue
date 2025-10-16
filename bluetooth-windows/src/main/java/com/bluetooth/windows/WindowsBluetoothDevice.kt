package com.bluetooth.windows

import com.bluetooth.core.BluetoothConnection
import com.bluetooth.core.BluetoothDevice

class WindowsBluetoothDevice(
    private val info: BluetoothApis.BLUETOOTH_DEVICE_INFO
) : BluetoothDevice {

    override val name: String
        get() = String(info.szName).trimEnd('\u0000')

    override val address: String
        get() = info.Address.toString()

    override fun connect(): BluetoothConnection {
        throw NotImplementedError("Conexión Bluetooth aún no implementada en Windows")
    }

}