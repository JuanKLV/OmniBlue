package com.bluetooth.windows

import com.sun.jna.Library
import com.sun.jna.Native
import com.sun.jna.Pointer
import com.sun.jna.Structure

interface BluetoothApis : Library {
    companion object {
        val INSTANCE: BluetoothApis = Native.load("BluetoothAPIs", BluetoothApis::class.java)
    }

    class BLUETOOTH_DEVICE_SEARCH_PARAMS : Structure() {
        @JvmField var dwSize: Int = 0
        @JvmField var fReturnAuthenticated: Boolean = true
        @JvmField var fReturnRemembered: Boolean = true
        @JvmField var fReturnUnknown: Boolean = true
        @JvmField var fReturnConnected: Boolean = true
        @JvmField var fIssueInquiry: Boolean = true
        @JvmField var cTimeoutMultiplier: Byte = 2
        @JvmField var hRadio: Pointer? = null

        init {
            dwSize = size()
        }

        override fun getFieldOrder(): List<String> = listOf(
            "dwSize",
            "fReturnAuthenticated",
            "fReturnRemembered",
            "fReturnUnknown",
            "fReturnConnected",
            "fIssueInquiry",
            "cTimeoutMultiplier",
            "hRadio"
        )
    }

    class BLUETOOTH_DEVICE_INFO : Structure() {
        @JvmField var dwSize: Int = 0
        @JvmField var Address: Long = 0
        @JvmField var ulClassofDevice: Int = 0
        @JvmField var fConnected: Boolean = false
        @JvmField var fRemembered: Boolean = false
        @JvmField var fAuthenticated: Boolean = false
        @JvmField var stLastSeen: ByteArray = ByteArray(16)
        @JvmField var stLastUsed: ByteArray = ByteArray(16)
        @JvmField var szName = CharArray(248)

        init {
            dwSize = size()
        }

        override fun getFieldOrder(): List<String> = listOf(
            "dwSize",
            "Address",
            "ulClassofDevice",
            "fConnected",
            "fRemembered",
            "fAuthenticated",
            "stLastSeen",
            "stLastUsed",
            "szName"
        )

        fun copy(
            dwSize: Int = this.dwSize,
            Address: Long = this.Address,
            ulClassofDevice: Int = this.ulClassofDevice,
            fConnected: Boolean = this.fConnected,
            fRemembered: Boolean = this.fRemembered,
            fAuthenticated: Boolean = this.fAuthenticated,
            stLastSeen: ByteArray = this.stLastSeen.copyOf(),
            stLastUsed: ByteArray = this.stLastUsed.copyOf(),
            szName: CharArray = this.szName.copyOf()
        ): BLUETOOTH_DEVICE_INFO {
            return BLUETOOTH_DEVICE_INFO().apply {
                this.dwSize = dwSize
                this.Address = Address
                this.ulClassofDevice = ulClassofDevice
                this.fConnected = fConnected
                this.fRemembered = fRemembered
                this.fAuthenticated = fAuthenticated
                this.stLastSeen = stLastSeen
                this.stLastUsed = stLastUsed
                this.szName = szName
            }
        }
    }

    fun BluetoothFindFirstDevice(params: BLUETOOTH_DEVICE_SEARCH_PARAMS, info: BLUETOOTH_DEVICE_INFO): Pointer
    fun BluetoothFindNextDevice(hFind: Pointer, info: BLUETOOTH_DEVICE_INFO): Boolean
    fun BluetoothFindDeviceClose(hFind: Pointer): Boolean
}