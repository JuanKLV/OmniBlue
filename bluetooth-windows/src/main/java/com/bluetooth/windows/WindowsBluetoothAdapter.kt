package com.bluetooth.windows

import com.bluetooth.core.BluetoothAdapter
import com.bluetooth.core.BluetoothDevice

class WindowsBluetoothAdapter : BluetoothAdapter {
    override fun getDevices(): List<BluetoothDevice> {
        val devices = mutableListOf<BluetoothDevice>()

        val searchParams = BluetoothApis.BLUETOOTH_DEVICE_SEARCH_PARAMS()
        val info = BluetoothApis.BLUETOOTH_DEVICE_INFO()

        info.dwSize = info.size()

        val hFind = BluetoothApis.INSTANCE.BluetoothFindFirstDevice(searchParams, info)
        if (hFind != null) {
            devices.add(WindowsBluetoothDevice(info))
            while (BluetoothApis.INSTANCE.BluetoothFindNextDevice(hFind, info)) {
                val copy = info.copy()
                devices.add(WindowsBluetoothDevice(copy))
            }
            BluetoothApis.INSTANCE.BluetoothFindDeviceClose(hFind)
        }

        return devices

    }

    override fun isEnabled(): Boolean {
        return try {
            getDevices().isNotEmpty()
        } catch (e: Exception) {
            false
        }
    }
}