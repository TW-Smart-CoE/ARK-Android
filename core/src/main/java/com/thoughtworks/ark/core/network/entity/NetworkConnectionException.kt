package com.thoughtworks.ark.core.network.entity

import java.io.IOException

class NetworkConnectionException(msg: String? = "network is not connect") : IOException(msg)